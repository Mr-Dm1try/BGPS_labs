const Request = {
	getStudents() {
		return fetch("http://localhost:8080/students/all", {
			method: 'GET'
		}).then(res => res.json())
		.then(res => {
		  console.log('body:' + JSON.stringify(res))
          return res;
		});
	},
	getJournal() {
		return fetch("http://localhost:8080/journal", {
			method: 'GET'
		}).then(res => res.json())
		.then(res => {
		  console.log('body:' + JSON.stringify(res))
          return res;
		});
	},
	getMark(id) {
		return fetch("http://localhost:8080/marks/" + id, {
			method: 'GET'
		}).then(res => res.json())
		.then(res => {
		  console.log('body:' + JSON.stringify(res))
          return res;
		});
	},
};

export default Request