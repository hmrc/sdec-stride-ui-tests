#!/usr/bin/env bash

set -euo pipefail

BROWSER=${1:-chrome}
ENVIRONMENT=${2:-local}
HEADLESSFLAG=${3:-false}
PREVIOUSVERSIONFLAG=${4:-true}



# ---- Main acceptance run  ----
sbt scalafmtCheckAll scalafmtSbtCheck clean compile -Dbrowser="$BROWSER" -Denvironment="$ENVIRONMENT" -Dbrowser.option.headless="$HEADLESSFLAG" -Dbrowser.usePreviousVersion="$PREVIOUSVERSIONFLAG" "testOnly uk.gov.hmrc.ui.specs.* -- -n AcceptanceTests" testReport
