<!DOCTYPE html>
<html lang="en">
<head>
<title>Home Page</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="resources/js/htmx.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/output.css" />
</head>
<body>
	<div>
		<h1>Home Page</h1>
	</div>
	<div>
		<form action="logout" method="post">
			<button type="logout"
				class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Logout</button>
		</form>
	</div>

</body>
</html>