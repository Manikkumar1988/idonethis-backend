require 'rspec/expectations'
World(RSpec::Matchers)

ENV['TEST_ENV'] ||= 'http://localhost:4567'
$BASE_URL = ENV['TEST_ENV']
