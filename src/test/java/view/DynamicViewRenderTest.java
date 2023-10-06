package view;

import application.model.Article;
import application.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class DynamicViewRenderTest {


    @Test
    @DisplayName("")
    void test() throws Exception {
        String expectResult = "abc\n" +
                "nkladsnalks RENDER_COMPLETED is good\n" +
                "acbac\n" +
                "name is name1, email is email1\nname is name2, email is email2\n";

        //given
        ModelAndView modelAndView = new ModelAndView("/index_test.html");

        User user1 = new User("id1", "pa", "name1", "email1");
        User user2 = new User("id2", "pa", "name2", "email2");
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        modelAndView.addAttribute("userList", userList);
        modelAndView.addAttribute("test", "RENDER_COMPLETED");

        //when
        String result = new String(DynamicViewRender.getInstance().render(modelAndView), StandardCharsets.UTF_8);

        //then
        Assertions.assertEquals(expectResult, result);
    }


    @Test
    void test1() throws Exception {
        //given
        ModelAndView modelAndView = new ModelAndView("/index_test.html");

        Article article1 = new Article("user1", "username1", "title1", "contents1");
        Article article2 = new Article("user2", "username2", "title2", "contents2");
        Article article3 = new Article("user3", "username3", "title3", "contents3");

        List<Article> articleList = new ArrayList<>();
        articleList.add(article1);
        articleList.add(article2);
        articleList.add(article3);

        modelAndView.setLogin("hong-gil-dong");
        modelAndView.addAttribute("articleList", articleList);

        //when
        String result = new String(DynamicViewRender.getInstance().render(modelAndView), StandardCharsets.UTF_8);

        //then
        System.out.println(result);
    }
}