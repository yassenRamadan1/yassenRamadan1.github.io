#!/bin/bash
set -e

KOBWEB_CLI_VERSION="0.9.16"

echo "Installing Kobweb CLI v${KOBWEB_CLI_VERSION}..."

# Download Kobweb CLI
curl -L "https://github.com/varabyte/kobweb-cli/releases/download/v${KOBWEB_CLI_VERSION}/kobweb-${KOBWEB_CLI_VERSION}.zip" -o kobweb.zip

# Unzip
unzip -q kobweb.zip

# Rename to kobweb-cli
mv "kobweb-${KOBWEB_CLI_VERSION}" kobweb-cli

# Make executable
chmod +x kobweb-cli/bin/kobweb

# Clean up
rm kobweb.zip

echo "Kobweb CLI installed successfully!"