package testcucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Stepdefs
{
    //driver
    WebDriver driver;

    //reused variables
    WebElement strapline;
    By teamMembersIdentifier = By.className("stack-img-content");
    List<WebElement> teamMembers;

    @Given("I navigate to the google search page")
    public void i_navigate_to_the_google_search_page()
    {
        //opens the browser to the url http://www.google.com
        driver.get("http://www.google.com");
        driver.manage().window().maximize();
    }

    @When("I search for “TruNarrative”")
    public void i_search_for_TruNarrative()
    {
        //enter the search term for TruNarrative in google
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("TruNarrative");
        searchBox.submit();
    }

    @Then("the first search result points to https:\\/\\/trunarrative.com\\/")
    public void the_first_search_result_points_to_https_trunarrative_com()
    {
        //List of google search result links
        List<WebElement> matchingResults = driver.findElements(By.className("LC20lb"));

        //click on first search result
        matchingResults.get(0).click();

        //verify that URL is as expected
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://trunarrative.com/", currentUrl);
    }

    @Given("I am viewing the homepage of the trunarrative website")
    public void i_am_viewing_the_homepage_of_the_trunarrative_website() {

        //open the homepage of the website
        driver.get("https://trunarrative.com/");
    }

    @When("I view the strapline")
    public void i_view_the_strapline()
    {
        //view the strapline on the homepage
        strapline = driver.findElements(By.className("bigger")).get(0);
    }

    @Then("the text displayed is {string}")
    public void the_text_displayed_is(String string)
    {

        //verify the strapline on the homepage
        assertEquals(strapline.getText()
                , "Easy Onboarding.  Smooth Transactions.  Insightful Compliance.");
    }

    @Given("I navigate to the trunarrative team page")
    public void i_navigate_to_the_trunarrative_team_page() {

        //navigate to the website and maximize so more button on menu is visible
        driver.get("https://trunarrative.com/");
        driver.manage().window().maximize();

        //find and click more button
        WebElement moreMenu = driver.findElement(By.id("menu-item-6055"));
        moreMenu.click();

        //find and click team button
        WebElement team = driver.findElement(By.id("menu-item-6388"));
        team.click();
    }

    @When("I view the leadership team")
    public void i_view_the_leadership_team()
    {
    }

    @Then("the following will be displayed")
    public void the_following_will_be_displayed(io.cucumber.datatable.DataTable dataTable) {

        //get list of team members from the elements on the page
        List<WebElement> teamMembers = driver.findElements(teamMembersIdentifier);

        //get the values from the datatable
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        //verify that there are 9 members of the team
        assertEquals(9, teamMembers.size());

        for (int i = 0; i < list.size(); i++)
        {
            //iterate table values
            String dataTableName = list.get(i).get("Name");
            String dataTableRole = list.get(i).get("Role");

            //iterate through names and roles from webelement
            String webElementName = driver.findElements(By.tagName("h2")).get(i).getText();
            String webElementRole = driver.findElements(By.className("sub")).get(i).getText();

            //check that each person in the table is assigned the correct role on the website
            if (dataTableName.equals(webElementName))
            {
                assertEquals(dataTableRole, webElementRole);
            }
        }
    }

    @Before
    public void startUp()
    {
        //starts up chromedriver at the beginning of every test
        driver = new ChromeDriver();
    }

    @After
    public void tearDown()
    {
        //quits the browser at the end of every test
        driver.quit();
    }
}
