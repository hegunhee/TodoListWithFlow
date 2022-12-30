# TodoListWithFlow  
이 레포지토리는 Flow를 공부하기위해 만든것입니다.  
데이터를 받아오는거를 상황별로 나눠서 UseCase를 구현할 예정입니다.  
테스트나 flow의 사용에 어느정도 익숙해지면 프로젝트 'routiner'를 만들 예정입니다.  

## issues  
asLiveData 확장함수가 안되는 이슈  
https://github.com/hegunhee/TodoListWithFlow/issues/1  
Flow Combine 사용  
https://github.com/hegunhee/TodoListWithFlow/issues/2  

## 완전한 flow로 이전 (LiveData -> Flow, 2022_12_28(수))  
Room DB에서 flow로 데이터를 받고있지만 정작 presentation layer에서 데이터를 사용하는건  
flow가 아닌 LiveData이므로 완전한 flow로 전환했습니다.  
사용하는 케이스에따라 Flow, StateFlow, SharedFlow를 사용하였습니다.  
