#encoding: utf-8

require 'rest-client'


When("I hit the API") do
  response = RestClient.get 'http://localhost:4567/hello'
  expect(response.code).to eq(200)
end

Then("hello world should return") do
  response = RestClient.get 'http://localhost:4567/hello'
  expect(response.code).to eq(200)
  data = response.body
end



