#!/usr/bin/env bash

BROWSER=${1:-chrome}
ENVIRONMENT=${2:-local}
HEADLESS=${3:-true}

sbt clean prePrChecks -Dbrowser="$BROWSER" -Denvironment="$ENVIRONMENT" -Dbrowser.option.headless="$HEADLESS" -Dsecurity.assessment=false "testOnly uk.gov.hmrc.ui.specs.* -- -n AcceptanceTests" testReport
