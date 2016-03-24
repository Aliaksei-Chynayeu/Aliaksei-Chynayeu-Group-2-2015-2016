//just good sample
//http://coenraets.org/blog/2011/12/restful-services-with-jquery-and-java-using-jax-rs-and-jersey/


//DELETE by id
$.ajax({
    url: 'http://localhost:8080/task-manager-service/task/123',
    type: 'DELETE',
       success: function() { alert('DELETE task 123 completed'); }
});

//POST xml
$.ajax({
    type: 'POST',
    contentType: 'application/xml',
    url: "http://localhost:8080/task-manager-service/task",
    dataType: "xml",
    data: '<task id="123"><name>xml-task</name><description>xml-task</description></task>',
    success: function (res) {
    	console.log("XML-POST: task was created.");
	},
	error: function (res) {
		console.log("XML-POST: task wasn't created.");
	}
});

//POST json
$.ajax({
    type: 'POST',
    contentType: 'application/json',
    url: "http://localhost:8080/task-manager-service/task",
    dataType: "json",
    data: '{"name":"json-task","description":"json-task","assignedUsers":[],"deadline":null,"startDate":null,"finishDate":null,"id":7745030332862201276}',
    success: function (res) {
    	console.log("JSON-POST: task was created.");
	},
	error: function (res) {
		console.log("JSON-POST: task was NOT created.");
	}
});

//GET all xml
var responce = $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/task-manager-service/task',
    dataType: "json", // data type of response
    success: function (res) {
    	console.log("XML-GET-all: all tasks were taken.");
	},
	error: function (res) {
		console.log("XML-GET-all: all tasks were NOT taken.");
	}
	async: false,
});

//GET all json
$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/task-manager-service/task',
    dataType: "json", // data type of response
    success: function (res) {
    	console.log("JSON-GET-all: all tasks were taken.");
	},
	error: function (res) {
		console.log("JSON-GET-all: all tasks were NOT taken.");
	}
});

//GET by id xml
$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/task-manager-service/task/123',
    dataType: "xml", // data type of response
    success: function (res) {
    	console.log("XML-GET-id: task 123 was taken.");
	},
	error: function (res) {
		console.log("XML-GET-id: task 123 was NOT taken.");
	}
});

//GET by id json
$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/task-manager-service/task/123',
    dataType: "json", // data type of response
    success: function (res) {
    	console.log("JSON-GET-id: task 123 was taken.");
	},
	error: function (res) {
		console.log("JSON-GET-id: task 123 was NOT taken.");
	}
});

//UPDATE xml
$.ajax({
    type: 'PUT',
    contentType: 'application/xml',
    url: "http://localhost:8080/task-manager-service/task",
    dataType: "xml",
    data: '<task id="123"><name>put-xml-task</name><description>put-xml-task</description></task>',
    success: function (res) {
    	console.log("XML-UPDATE: created task!");
	},
	error: function (res) {
		console.log("XML-UPDATE: task wasn't created! ");
	}
});

// UPDATE json
$.ajax({
    type: 'PUT',
    contentType: 'application/json',
    url: "http://localhost:8080/task-manager-service/task",
    dataType: "json",
    data: '{"name":"put-json-task","description":"put-json-task","assignedUsers":[],"deadline":null,"startDate":null,"finishDate":null,"id":7745030332862201276}',
    success: function (res) {
    	console.log("JSON-UPDATE: created task!");
	},
	error: function (res) {
		console.log("JSON-UPDATE: task wasn't created! ");
	}
});


