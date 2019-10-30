#encoding: utf-8

require 'rest-client'
require 'json'

When("I request for send reminder") do
    response = RestClient.post "#{$BASE_URL}/team/1/remind", "", {content_type: :json,accept: :json}
    expect(response.code).to eq(200)
end

Then("reminder mail should be send to all members") do

end