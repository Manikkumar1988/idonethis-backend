#encoding: utf-8

require 'rest-client'
require 'json'

When("I login valid username and password") do
    response = RestClient.get "#{$BASE_URL}/user", {content_type: :json,accept: :json,:userId => 'test1@gmail.com',:password => 'pass'}
    expect(response.code).to eq(200)
    aFile = File.open("input.txt", "w")
    aFile.write(response.body)
    aFile.close
end

Then("I should be able to add a to_do item") do
    data = File.read("input.txt")
    parsed = JSON.parse(data)
    response = RestClient.post "#{$BASE_URL}/user/#{parsed["uid"]}/item",{item:"task A", type:0} , {content_type: :json, accept: :json}
    expect(response.code).to eq(200)
    expect(response.body).to eq("")
end