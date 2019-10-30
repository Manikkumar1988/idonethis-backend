#encoding: utf-8

require 'rest-client'
require 'json'

When("I add a team member with mail id") do
    payload = """
      {
        \"email\" : \"abc@gmail.com\"
      }
      """
    response = RestClient.post "#{$BASE_URL}/team/1", payload, {content_type: :json,accept: :json}
    expect(response.code).to eq(200)
end

Then("I should be able to see the added mail id in the full team list") do
    response = RestClient.get "#{$BASE_URL}/team/1" , {content_type: :json, accept: :json}
    expect(response.code).to eq(200)
    expect(response.body).to eq("[{\"email\":\"test1@gmail.com\",\"teamid\":\"1\"},{\"email\":\"test2@gmail.com\",\"teamid\":\"1\"},{\"email\":\"test3@gmail.com\",\"teamid\":\"1\"},{\"email\":\"abc@gmail.com\",\"teamid\":\"1\"}]")
end