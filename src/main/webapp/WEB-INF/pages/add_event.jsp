<!-- Author:M. ALI -->
<div class="modal fade" id="addEvent" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">

			<div class="modal-header" style="padding: 35px 50px;">
				<h4>
					<span class="glyphicon glyphicon-plus"></span> Add event
				</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body" style="padding: 40px 50px;">
				<div class="limiter">
					<div class="container-login100">
						<div class="wrap-login100">
							<form method="POST" id="addEventForm" action="addEvent.htm" class="login100-form validate-form">
								<div class="wrap-input100 validate-input"
									data-validate="Title is required">
									<input placeholder="Title" class="input100" value="Mohamed Ali"
										name="name" type="text" />
									<span class="focus-input100"></span>
								</div>

								<div class="wrap-input100 validate-input"
									data-validate="Description is required">
									<input placeholder="Description" class="input100" value="abc"
										name="shortDescription" type="text" />
									<span class="focus-input100"></span>
								</div>

								<div class="wrap-input100 validate-input"
									data-validate="Address is required">
									<input placeholder="Address" class="input100" value="abc"
										name="address" type="text" />
									<span class="focus-input100"></span>
								</div>

								<div class="wrap-input100 validate-input"
									data-validate="Start date is required">
									<input placeholder="Start date" class="input100" value="2019-05-23"
										name="startDate" type="date" />
									<span class="focus-input100"></span>
								</div>

								<div class="wrap-input100 validate-input"
									data-validate="End date is required">
									<input placeholder="End date" class="input100" value="2019-05-24"
										name="endDate" type="date" />
									<span class="focus-input100"></span>
								</div>

								<div class="container-login100-form-btn">
									<button class="login100-form-btn" type="submit" id="addEventSubmit">Add</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>