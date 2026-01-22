## Episode 5 – Defining the System Under Test

This repository is part of the Q-volution series and focuses on **defining the system under test explicitly**, so QA results are based on facts instead of assumptions.

In many setups, QA inherits an environment where the application is already running. Tests may pass or fail, but it’s often unclear **what exactly was running, in which order, and when the system was actually ready**. This episode addresses that gap.

### What this repo shows

* How to define **which services make up the system under test**
* How to pin **versions**, define **startup order**, and verify **readiness**
* How the same system definition can be used locally and on a dynamic test environment

### Repository structure

* **/terraform**
  Creates a disposable test environment (infrastructure only).

* **/ansible**
  Prepares the environment so it is test-ready (tools, access, prerequisites).

* **/app**
  Defines how the system under test actually runs: services, versions, dependencies, and health checks.

Each part has a clear responsibility. Terraform builds the environment, Ansible prepares it, and this episode focuses on **making execution explicit**.

### Why this matters for QA

When execution is defined, tests are no longer guessing.
They validate a known system, started in a known way, producing results QA can trust.

