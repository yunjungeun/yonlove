package com.example.yoonlove.service;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagingService {

    @Autowired
    private PageMapper pageMapper;
    @Autowired
    private CsMapper csMapper;
    @Autowired
    private CreatorMapper creatorMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ScenarioMapper scenarioMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ScriptPaperMapper scriptPaperMapper;

    @Autowired
    private ProjectMapper projectMapper;


    public PageDto paging(PageDto dto){
        PageDto pageDto = new PageDto();


       //dto 리턴값 = 전체게시글 구하는 sql 실행 메소드
        switch (dto.getTable()){
            case "notice" : pageDto = csMapper.totalNoticePost(dto);break;
            case "qna" : pageDto = csMapper.totalQnAPost(dto); break;
            case "actor" : pageDto = sceneMapper.totalActorPost(dto); break;
            case "actor_managment" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "budget" : pageDto = projectMapper.totalBudgetPost(dto); break;
            case "company" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "creater" : pageDto = creatorMapper.totalCreatorPost(dto); break;
            case "creater_profit" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "department" : pageDto = adminMapper.totalDptPost(dto); break;
            case "film_plan" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "log" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "member" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "produce" : pageDto = projectMapper.totalProducePost(dto); break;
            case "project" : pageDto = projectMapper.totalProjectPost(dto); break;
            case "scenario" : pageDto = scenarioMapper.totalScenarioPost(dto); break;
            case "scene" : pageDto = sceneMapper.totalScenePost(dto); break;
            case "schedule_day" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "schedule_month" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "schedule_time" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;
            case "scriptpaper" : pageDto = scriptPaperMapper.totalScriptPost(dto); break;
            case "timetable" : pageDto = scriptPaperMapper.totalTimeTablePost(dto); break;
            case "users" : pageDto= adminMapper.totalUserPost(dto); break;
            case "video" : System.out.println("pageing서비스의 paging 메서드 안에 스위치 문 pageDto = '컨트롤러명'Mapper.total'테이블명'Post(dto) 미작성"); break;

            //오류메세지 출력
            default:
                System.out.println("Paging서비스의 paging 메소드 오류 : 특정 게시판의 총 페이지를 구하는 메소드를 선택되지 못했습니다" +
                        " dto.getTable() 값이 잘못되었거나 case의 값이 잘못되었을 가능성/ dto.getTable ="+dto.getTable()
                + "    /입력받은 case명 : "+dto.getTable());
        }


        //전체 게시글의 갯수 값
        int totalPost = pageDto.getTotalPost();
        //전체 페이지의 갯수 값
        int totalPage = (totalPost-1) / dto.getPostCnt() + 1;
        pageDto.setPageCnt(totalPage);

        //시작페이지 //     (현재페이지 번호        -1   / 페이징사이즈)      * 페이징 사이즈     + 1
        int pageStart = ((dto.getCurrentPage() - 1) / dto.getPaging()) * dto.getPaging() + 1;
        pageDto.setPageStart(pageStart);

        //끝페이지  =             (시작페이지 + 페이징 사이즈     -1)와 총페이지 중 작은것
        int pageEnd = Math.min((pageStart + dto.getPaging() - 1),totalPage);
        pageDto.setPageEnd(pageEnd);

        //  마지막글번호 =             (현재페이지         *  페이지당 글 수 )와 전체글 중 작은 값
        int postEndNum = Math.min((dto.getCurrentPage()*dto.getPostCnt()),totalPost);
        pageDto.setPostEnd(postEndNum);

        //  시작글 번호   =  마지막 글버놓 - (  페이지당 글수   - 1)
        int postStartNum = postEndNum - (dto.getPostCnt() - 1);
        pageDto.setPostStart(postStartNum);

        //이전 다음버튼 관련 값
        pageDto.setHasPre(dto.getCurrentPage() > 1);
        pageDto.setHasNext(dto.getCurrentPage() < pageEnd);
        pageDto.setPrePage(dto.getCurrentPage()-1);
        pageDto.setNextPage(dto.getCurrentPage()+1);

        //검색관련된 내용을 저장
        pageDto.setPkid(dto.getPkid());
        pageDto.setPkintid(dto.getPkintid());
        pageDto.setWriter(dto.getWriter());
        pageDto.setContent(dto.getContent());
        pageDto.setTitle(dto.getTitle());
        pageDto.setDate(dto.getDate());
        pageDto.setSearch(dto.getSearch());

        //페이징 테이블 관련된 내용을 저장
        pageDto.setTable(dto.getTable());
        pageDto.setId(dto.getId());

        return pageDto;
    }

    //뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드  //매개변수는 paging 매소드값의 결과이다.
    public List<PageDto> pageList(int pageStart, int pageEnd, int currentPage){
        List<PageDto> pagelist = new ArrayList<>();
        for(int i = pageStart; i<= pageEnd; i++){
            PageDto pageFlag = new PageDto(i, i==currentPage);
            pagelist.add(pageFlag);
        }
        return pagelist;
    }

    //이전 다음버튼에 대한 생성 메서드
    public PageDto pagingFlag(int page, int pageEnd){
        PageDto pageDto = new PageDto();
        pageDto.setHasPre(page > 1);
        pageDto.setHasNext(page < pageEnd);
        pageDto.setPrePage(page-1);
        pageDto.setNextPage(page+1);
        return pageDto;
    }

    //검색유무에 따라 페이지 링크를 동적으로 생성
    public String pageRink(PageDto dto){
        String rink;
        String type=null;
        String keyword=null;
        String[] keywords = {dto.getTitle(), dto.getWriter(), dto.getContent(), dto.getPkid()};
        String[] types = {"title", "writer", "content", "pkid"};

        //검색을 했는지 안했는지 검출하는 for문// 검색어가 있다면 검색어(keyword)와 검색타입(type)을 검출함
        for (int i = 0 ; i < keywords.length; i++){
            if(keywords[i] != null ){
                keyword= keywords[i];
                type = types[i];
                break;
            }
        }
        //검색 값이 없으면 일반적인 페이지링크를 만들고 값이 있다면 검색어에 대한 페이지링크 생성
        if(keyword == null){
            rink = dto.getTable()+"?page=";
        }else {
            rink = dto.getTable() + "?" + type+ "=" +keyword + "&page=";
        }
        return rink;
    }

    public String subPageRink(PageDto dto, String upwardPostTable){
        String rink;
        String type=null;
        String keyword=null;
        String[] keywords = {dto.getTitle(), dto.getWriter(), dto.getContent(), dto.getPkid()};
        String[] types = {"title", "writer", "content", "pkid"};

        //검색을 했는지 안했는지 검출하는 for문// 검색어가 있다면 검색어(keyword)와 검색타입(type)을 검출함
        for (int i = 0 ; i < keywords.length; i++){
            if(keywords[i] != null ){
                keyword= keywords[i];
                type = types[i];
                break;
            }
        }
        //검색 값이 없으면 일반적인 페이지링크를 만들고 값이 있다면 검색어에 대한 페이지링크 생성
        if(keyword == null){
            rink = "select"+upwardPostTable+"?page=";
        }else {
            rink = "select"+upwardPostTable + "?" + type+ "=" +keyword + "&page=";
        }
        return rink;
    }

    public List<NoticeDto> postList(PageDto dto){return pageMapper.postList(dto);}

}
