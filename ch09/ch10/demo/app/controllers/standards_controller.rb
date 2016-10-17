class StandardsController < ApplicationController
  def unsecured
    render :text => "Anybody can read this meaningless message"
  end
end
