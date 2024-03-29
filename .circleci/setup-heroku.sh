#!/bin/bash

set -eu

git remote add heroku https://git.heroku.com/$HEROKU_APP_NAME_ONE.git
wget https://cli-assets.heroku.com/branches/stable/heroku-linux-amd64.tar.gz
sudo mkdir -p /usr/local/lib /usr/local/bin
sudo tar -xvzf heroku-linux-amd64.tar.gz -C /usr/local/lib
sudo ln -s /usr/local/lib/heroku/bin/heroku /usr/local/bin/heroku

cat > ~/.netrc << EOF
machine api.heroku.com
	login $HEROKU_USERNAME
	password $HEROKU_API_KEY
machine git.heroku.com
	login $HEROKU_USERNAME
	password $HEROKU_API_KEY
EOF

sudo chmod 600 ~/.netrc

# Add heroku.com to the list of known hosts
sudo ssh-keyscan -H heroku.com >> ~/.ssh/known_hosts