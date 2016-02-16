::!/bin/bash
::
:: Script for building and running unit tests
::
:: To Execute: 
::     bash gobuild
::

echo Launching build...

gradlew clean build jacocoTestReport
  echo Build complete. Test reports are available in build/reports. Launching firefox...
  ::firefox build/reports/tests/index.html build/reports/jacoco/test/html/index.html &
::fi
