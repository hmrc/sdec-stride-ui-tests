#!/usr/bin/env bash

set -euo pipefail

BROWSER=${1:-chrome}
ENVIRONMENT=${2:-local}
HEADLESSFLAG=${3:-false}
PREVIOUSVERSIONFLAG=${4:-true}

SBT_PROPS=(
  "-Dbrowser=$BROWSER"
  "-Denvironment=$ENVIRONMENT"
  "-Dbrowser.option.headless=$HEADLESSFLAG"
  "-Dbrowser.usePreviousVersion=$PREVIOUSVERSIONFLAG"
)

run_pre_pr_checks() {
  echo "==> Running pre-PR checks"

  # 1. Validate arguments
  if [[ "$HEADLESSFLAG" != "true" && "$HEADLESSFLAG" != "false" ]]; then
    echo "ERROR: HEADLESSFLAG must be 'true' or 'false'." >&2
    exit 1
  fi
  if [[ "$PREVIOUSVERSIONFLAG" != "true" && "$PREVIOUSVERSIONFLAG" != "false" ]]; then
    echo "ERROR: PREVIOUSVERSIONFLAG must be 'true' or 'false'." >&2
    exit 1
  fi

  # 2. Git checks
  if [[ -n "$(git status --porcelain)" ]]; then
    echo "ERROR: Uncommitted changes found. Commit or stash them before opening a PR." >&2
    exit 1
  fi

  current_branch=$(git rev-parse --abbrev-ref HEAD)
  if [[ "$current_branch" == "main" || "$current_branch" == "master" ]]; then
    echo "ERROR: You are on '$current_branch'. Create a feature branch first." >&2
    exit 1
  fi

  # 3. Formatting, clean compile, and unit tests
  sbt scalafmtCheckAll scalafmtSbtCheck clean compile Test/compile
  sbt test

  echo "==> Pre-PR checks passed"
}

# Skip with: SKIP_PRE_PR=true ./run.sh chrome local false true
if [[ "${SKIP_PRE_PR:-false}" != "true" ]]; then
  run_pre_pr_checks
fi


# ---- Main acceptance run  ----
sbt scalafmtCheckAll scalafmtSbtCheck clean compile \
  -Dbrowser="$BROWSER" \
  -Denvironment="$ENVIRONMENT" \
  -Dbrowser.option.headless="$HEADLESSFLAG" \
  -Dbrowser.usePreviousVersion="$PREVIOUSVERSIONFLAG" \
  "testOnly uk.gov.hmrc.ui.specs.* -- -n AcceptanceTests" testReport
