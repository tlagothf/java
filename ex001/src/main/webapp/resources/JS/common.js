/**
 * fetch 공통함수
 * post 방식으로 요청
 * ex )fatch_post('/changeAdminYN', data, 함수명)
 * callback : 바로 실행되지 않고 특정 지점에 실행 할 수 있도록 함수의 이름만 전달
 */
	function fetch_post(url, data, callback) {
		fetch(url, {
			method : 'post'
			// body 에 추가하는 데이터의 타입과 일치하는 타입을 지정
			, headers: {
			"Content-Type": "application/json",
			 // 'Content-Type': 'application/x-www-form-urlencoded',
			}
			// 오브젝트를 json 형식의 문자열로 변경
			, body : JSON.stringify(data)
		})
		// 받아온 데이터를 json형식으로 바꾸어 가져오는 코드
		.then(res=>res.json())
		.then(data=>{
			// 
			callback(data);
		})
		.catch(error=>{
			console.log('error', error)
		});
	}
	
