class AdminsController < ApplicationController
  def secured
    render :text => "This is top secret code"
  end
end
