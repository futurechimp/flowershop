#!/usr/bin/env bash
#
# A more capable sbt runner, coincidentally also called sbt.
# Author: Paul Phillips <paulp@typesafe.com>

# todo - make this dynamic
declare -r sbt_release_version=0.12.0
declare -r sbt_snapshot_version=0.13.0-SNAPSHOT

unset sbt_jar sbt_dir sbt_create sbt_snapshot sbt_launch_dir
unset scala_version java_home sbt_explicit_version
unset verbose debug quiet

for arg in "