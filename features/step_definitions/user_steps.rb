#encoding: utf-8

require 'rest-client'


When("I register using valid username and password") do
    response = RestClient.post "#{$BASE_URL}/user", '', {content_type: :json,accept: :json,:user => 'abc@test.com',:pass => 'pass@123'}
    expect(response.code).to eq(200)
end

Then("I should be able to login") do
    response = RestClient.get "#{$BASE_URL}/user", {content_type: :json, accept: :json, Authorization => 'Basic abc@test.com:pass@123'}
    expect(response.code).to eq(200)
end