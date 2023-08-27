#!/bin/bash

curl https://raw.githubusercontent.com/creationix/nvm/master/install.sh | bash > /dev/null
# shellcheck disable=SC1090
source ~/.profile
nvm install 20.4.0 > /dev/null
