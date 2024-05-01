package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFromControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="fronControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form",new MemberFromControllerV1());
        controllerMap.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members",new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("FrontControllerServletV1");

        String requestURI = request.getRequestURI();

        /* 부모는 자식을 담을수있다 그래서 부모인 ControllerV1(인터페이스)은 자식은 MemberListControllerV1 을 담을 수 있다  */
//        ControllerV1 controller = new MemberListControllerV1(); 사실은 얘다
        ControllerV1 controller = controllerMap.get(requestURI); /*그럼 여기서 가져오는게 ControllerV1 인터페이스라는건가 ? 그게 저 위에서 3개 생성될수있는 ?.. */
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request,response);
    }
}
