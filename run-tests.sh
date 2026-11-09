#!/usr/bin/env bash

BROWSER=${1:-chrome}
ENVIRONMENT=${2:-local}
HEADLESSFLAG=${3:-true}

sbt clean prePrChecks -Dbrowser="$BROWSER" -Denvironment="$ENVIRONMENT" -Dbrowser.option.headless="$HEADLESSFLAG" "testOnly uk.gov.hmrc.ui.specs.* -- -n AcceptanceTests" testReport
