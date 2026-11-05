#!/usr/bin/env bash

ENVIRONMENT=$1

sbt clean prePrChecks -Denvironment="${ENVIRONMENT:=local}" "testOnly uk.gov.hmrc.api.specs.*" testReport
