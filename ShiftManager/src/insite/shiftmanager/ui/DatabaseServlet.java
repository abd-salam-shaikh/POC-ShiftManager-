package insite.shiftmanager.ui;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.bean.Bean;
import core.bean.Beans;
import core.bo.Bo;
import core.dao.Dao;

@WebServlet("/database.do")
public class DatabaseServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        for (String name : Beans.getNames()) {
            if (name.endsWith("Dao")) {
                Object bean = Beans.get(Object.class, name);
                if (Dao.class.isInstance(bean)) {
                    Dao dao = (Dao) bean;
                    List<Object> results = new ArrayList<Object>();
                    dao.filter(null, results);
                    if (results.size() > 0) {
                        Bean b = new Bean(results.get(0).getClass());
                        b.addSkipCopyProperties(Bo.class, Collection.class);
                        for (int i = 0; i < results.size(); i++) {
                            results.set(i, b.getValues(results.get(i)));
                        }
                        map.put(results.get(0).getClass().getSimpleName(), results);
                    }
                }
            }
        }
        request.setAttribute("data", map);
        showPage(request, response);
    }

}
