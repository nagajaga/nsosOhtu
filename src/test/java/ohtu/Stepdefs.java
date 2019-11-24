package ohtu;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class Stepdefs {

    Work work;

    @Before
    public void setup() {
    }

    @Given("bookmark of a book is initialized")
    public void bookmarkOfABookIsInitialized() throws Throwable {
        work = new Work("", "");
    }

    @When("bookmark title is set to {string}")
    public void bookmarkTitleIsSet(String title) {
        work.setTitle(title);
    }

    @When("bookmark author is set to {string}")
    public void bookmarkAuthorIsSet(String author) {
        work.setAuthor(author);
    }

    @Then("the title should be {string} and author {string}")
    public void theTitleAndAuthorShouldBe(String title, String author) {
        assertTrue(work.getTitle().equals(title));
        assertTrue(work.getAuthor().equals(author));
    }
}
