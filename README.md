<h2>JPA</h2>

<details>
  <summary>
    04.17
  </summary>
  <ul dir="auto">
    <br>
    
    jpql (sql문 사용)
    queryDSL (jsql에서 좀 더 발전)

    Model 값 출력
     ${} : EL
     <c:forEach> : JSTL -> for 문 대신 처리 class
     자주쓰는 명령어를 tag로 바꾼,
    
    Controller
      model.addAttribute("name", "shin" );
      return "view"  // view.jsp에 전달
      
    view.jsp  ->
      <% =model.getAttribute("name") %>  // java 문법 꺼낼 때
      ${ name }

    -----------------------
    bean : instance static (static = 한 개만 가능)
    
    Class car { } class : car</ul>
    
    static - compile시 미리 생성

    
    Reference type
      Car myCar = new Car();  // heap (tree)
      instance : myCar

    
    Primitive type
      int num = 3;     // stack : 메모리에서 일부만 동작
      instance : num   // 
</details>
<br>
<details>
  <summary>
    04.18
  </summary>
  <ul dir="auto">
    <br>
    <li>Mustache 문법 참조 사이트 : https://bibi6666667.tistory.com/269</li>

    Spring      <->        jap        <->              h2 db
                           orm
                           java 문법(class 사용)
                           .save(boardVo) - (insert)
                           sql -> (대신) JPQL or QueryDSL
                           
    {{ }} = model.getAttribute                       

    a href & from tag = GetMapping
    reqmap 대신 rest (하나의 주소를 method를 바꿔서 다른일을 하게끔 : CRUD)

    form tag안 input type들을 request
    name 안의 변수를(name="title") Controller에 전송
    submit 누르면 action의 주소로 이동

    POST로 적으면 F12 payload에 form data (입력한 데이터가 담겨서 전송)

    ---------------------------

    Regacy = 변수로 받아짐
    Boot   = 객체로 받는 형태로 바뀜 (Dto = Vo)

    ---------------------------

                         entity가 생성&실행 ->  h2 create table 수행

                        class(vo)       class(entity)        jpa
    write.mustache  ->  articleDto  ->  Article       ->                 ->       h2
    title               title           id                 .save()               Article table
    content             content         title               (INSERT)
                                        content


    -----------------------------------

    정보 전달법
      POST 방식 From Data    : /articles/Write
      Get  방식 QueryStirng  : /articles?id=1  (데이터가 QS에 담김)
               PathVariable : /articles/1  -> REST에서 사용 하는 GET 방식

    
    /*
    자료를 하나씩 채우는 형식
    ArrayList 사이즈가 0이됨
    
    List<MenuVo> menuList = new ArrayList<>();
    if(menuList.size() == 0 ) // 자료가 없으면
    while 문 돌리면서 조회..?
    
    
    
    MenuVo  menuVo = null;
     menuVo = new MenuVo(1, '');
    if(menuVo == null) // 조회한 자료가 없으면
    
    
    한 개만 조회했으니 orElse
    */
    
    
</details>
<br>
<details>
  <summary>
    04.19
  </summary>
  <ul dir="auto">
    <br>
    <li>Spring 공홈 JPA 참조 사이트 : https://spring.io/projects/spring-data-jpa#samples</li>

    persist 영속 : entity를 .save 명령으로 insert

    hibernate에서 orm을 지원/사용
    ORM = java class db 연결

    -----------------------------------------------------

    				                                  @Entity               h2 db table
    write.mustache   ->    ArticleDto    ->    Article         ->   Article
    title                  title               id(번호자동증가)
    content                content             title 
                                               content
    
    
    
    p197 ~ 변경 수정, 삭제
    
    		                   vo		                @Entity               h2 db table
    write.mustache   ->    ArticleForm    ->    Article         ->    Article
    title                  id                   id(번호자동증가)
    content                content              title 
                           title                content
                                                getter/setter

    teg -> 이미지로 = view rendering

    -----------------------------------------------------------

    REST API

    페이지 이동 x --> view가 다함(html js, mustache) CRUD
     결과 data만 client에 내려보내는 형식
      data(json{대표}, xml, html, csv) --> server 기능만 하게끔.
         html + js => 서버 데이터 요청
         이 데이터는 json/xml으로 내려옴 
          -> 1. js(XHR: XMLHttpRequest)로 불러옴 (옛날 방식 - 개념만 알고)
             2. $.ajax() -> jquery 사용 (읽을 줄 알아야함) 
             3. axios
             4. fetch()
         

    /Board
    method
    GET    ->   @GetMapping("/Board")     -->  select
    GET    ->   @GetMapping("/Board/2")   -->  selecct where
    POST   ->   @PostMapping("/Board")    -->  insert
    
    -------------------------------------------------------------
    
    PUT    ->   @PutMapping("/Board")     -->  update (전체) where
    PATCH  ->   @PatchMapping("/Board")   -->  update (부분) where
    DELET  ->   @DeleteMapping("/Board")  -->  delete where 





    /Board/WriteForm --> 예전
    입력받으면 return해서 mus에 보내거나 jsp로 보냄
    페이지 이동을 cont가 담당
    
</details>
<br>
<details>
  <summary>
    04.
  </summary>
  <ul dir="auto">
    <br>
    <li></li>


