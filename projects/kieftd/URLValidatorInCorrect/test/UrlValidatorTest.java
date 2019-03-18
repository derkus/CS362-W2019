import junit.framework.TestCase;
//import random class
import java.util.Random; 

public class UrlValidatorTest extends TestCase {

  private boolean printStatus = false;
  private boolean printIndex = false; // print index that indicates current scheme,host,port,path, query test were using.

  public UrlValidatorTest(String testName) {
    super(testName);
  }

  public void testManualTest()
  {
    UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

    System.out.println("\nAllowing all schemes: \n");
    // scheme
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal.isValid("http://www.google.com"));
    System.out.println("www.google.com");
    System.out.println(urlVal.isValid("www.google.com"));
    System.out.println("h3tp://www.google.com");
    System.out.println(urlVal.isValid("h3tp://www.google.com"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http:/www.google.com");
    System.out.println(urlVal.isValid("://www.google.com"));
    System.out.println("://www.google.com");
    System.out.println(urlVal.isValid("://www.google.com"));
  
    // authority
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal.isValid("http://www.google.com"));
    System.out.println("http://www.google.au");
    System.out.println(urlVal.isValid("http://www.google.au"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google~.com");
    System.out.println(urlVal.isValid("http://www.google~.com"));
    System.out.println("http://www.google");
    System.out.println(urlVal.isValid("http://www.google"));
    System.out.println("http://broke.hostname.com");
    System.out.println(urlVal.isValid("http://bad.hostname.com"));
    System.out.println("http://1.2.3");
    System.out.println(urlVal.isValid("http://1.2.3"));
    System.out.println("http://<blank>");
    System.out.println(urlVal.isValid("http:// "));
  
   
    // port
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal.isValid("http://www.google.com:80"));
    System.out.println("http://www.google.com:300");
    System.out.println(urlVal.isValid("http://www.google.com:300"));
    System.out.println("http://www.google.com:3000");
    System.out.println(urlVal.isValid("http://www.google.com:3000"));
    System.out.println("http://www.google.com:30000");
    System.out.println(urlVal.isValid("http://www.google.com:30000"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google.com:");
    System.out.println(urlVal.isValid("http://www.google.com:"));
    System.out.println("http://www.google.com:-80");
    System.out.println(urlVal.isValid("http://www.google.com:-80a"));
    System.out.println("http://www.google.com:80a");
    System.out.println(urlVal.isValid("http://www.google.com:80a"));

   // path
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1");
    System.out.println(urlVal.isValid("http://www.google.com/test1"));
    System.out.println("http://www.google.com/");
    System.out.println(urlVal.isValid("http://www.google.com/"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1");
    System.out.println(urlVal.isValid("http://www.google.comtest1"));
    System.out.println("http://www.google.com//test1");
    System.out.println(urlVal.isValid("http://www.google.com//test1"));
    System.out.println("http://www.google.com/..");
    System.out.println(urlVal.isValid("http://www.google.com/.."));
    System.out.println("http://www.google.com/../");
    System.out.println(urlVal.isValid("http://www.google.com/../"));

    
 
    // option
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1/test1");
    System.out.println(urlVal.isValid("http://www.google.com/test1/test1"));
    System.out.println("http://www.google.com/test1/");
    System.out.println(urlVal.isValid("http://www.google.com/test1/"));
    System.out.println("http://www.google.com/test1test1");
    System.out.println(urlVal.isValid("http://www.google.com/test1test1"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1test1");
    System.out.println(urlVal.isValid("http://www.google.comtest1test1"));
    System.out.println("http://www.google.com/test1//test1");
    System.out.println(urlVal.isValid("http://www.google.com/test1//test1"));

    // queries
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com?action=view");
    System.out.println(urlVal.isValid("http://www.google.com?action=view"));
    System.out.println("http://www.google.com?action=view&hi=hello");
    System.out.println(urlVal.isValid("http://www.google.com?action=view&hi=hello"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.com?action");
    System.out.println(urlVal.isValid("http://www.google.com?action="));
    System.out.println("http://www.google.com=view");
    System.out.println(urlVal.isValid("http://www.google.comaction=view"));
    System.out.println("http://www.google.com??action=view");
    System.out.println(urlVal.isValid("http://www.google.com??action=view"));

    UrlValidator urlVal2 = new UrlValidator(null, null, UrlValidator.NO_FRAGMENTS);
    System.out.println("\nAllowing No Fragments: \n");
    // scheme
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal2.isValid("http://www.google.com"));
    System.out.println("www.google.com");
    System.out.println(urlVal2.isValid("www.google.com"));
    System.out.println("h3tp://www.google.com");
    System.out.println(urlVal2.isValid("h3tp://www.google.com"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http:/www.google.com");
    System.out.println(urlVal2.isValid("://www.google.com"));
    System.out.println("://www.google.com");
    System.out.println(urlVal2.isValid("://www.google.com"));

    // authority
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal2.isValid("http://www.google.com"));
    System.out.println("http://www.google.au");
    System.out.println(urlVal2.isValid("http://www.google.au"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google~.com");
    System.out.println(urlVal2.isValid("http://www.google~.com"));
    System.out.println("http://www.google");
    System.out.println(urlVal2.isValid("http://www.google"));
    System.out.println("http://broke.hostname.com");
    System.out.println(urlVal2.isValid("http://broke.hostname.com"));
    System.out.println("http://1.2.3");
    System.out.println(urlVal2.isValid("http://1.2.3"));
    System.out.println("http://<blank>");
    System.out.println(urlVal2.isValid("http:// "));

    // port
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal2.isValid("http://www.google.com:80"));
    System.out.println("http://www.google.com:300");
    System.out.println(urlVal2.isValid("http://www.google.com:300"));
    System.out.println("http://www.google.com:3000");
    System.out.println(urlVal2.isValid("http://www.google.com:3000"));
    System.out.println("http://www.google.com:30000");
    System.out.println(urlVal2.isValid("http://www.google.com:30000"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google.com:");
    System.out.println(urlVal2.isValid("http://www.google.com:"));
    System.out.println("http://www.google.com:-80");
    System.out.println(urlVal2.isValid("http://www.google.com:-80a"));
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal2.isValid("http://www.google.com:80a"));

    // path
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1");
    System.out.println(urlVal2.isValid("http://www.google.com/test1"));
    System.out.println("http://www.google.com/");
    System.out.println(urlVal2.isValid("http://www.google.com/"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1");
    System.out.println(urlVal2.isValid("http://www.google.comtest1"));
    System.out.println("http://www.google.com//test1");
    System.out.println(urlVal2.isValid("http://www.google.com//test1"));
    System.out.println("http://www.google.com/..");
    System.out.println(urlVal2.isValid("http://www.google.com/.."));
    System.out.println("http://www.google.com/../");
    System.out.println(urlVal2.isValid("http://www.google.com/../"));

    // option
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1/test1");
    System.out.println(urlVal2.isValid("http://www.google.com/test1/test1"));
    System.out.println("http://www.google.com/test1/");
    System.out.println(urlVal2.isValid("http://www.google.com/test1/"));
    System.out.println("http://www.google.com/test1test1");
    System.out.println(urlVal2.isValid("http://www.google.com/test1test1"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1test1");
    System.out.println(urlVal2.isValid("http://www.google.comtest1test1"));
    System.out.println("http://www.google.com/test1//test1");
    System.out.println(urlVal2.isValid("http://www.google.com/test1//test1"));

    // queries
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com?action=view");
    System.out.println(urlVal2.isValid("http://www.google.com?action=view"));
    System.out.println("http://www.google.com?action=view&hi=hello");
    System.out.println(urlVal2.isValid("http://www.google.com?action=view&hi=hello"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.com?action");
    System.out.println(urlVal2.isValid("http://www.google.com?action="));
    System.out.println("http://www.google.com=view");
    System.out.println(urlVal2.isValid("http://www.google.comaction=view"));
    System.out.println("http://www.google.com??action=view");
    System.out.println(urlVal2.isValid("http://www.google.com??action=view"));

    UrlValidator urlVal3 = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
    System.out.println("\nAllowing local urls: \n");
    // scheme
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal3.isValid("http://www.google.com"));
    System.out.println("www.google.com");
    System.out.println(urlVal3.isValid("www.google.com"));
    System.out.println("h3tp://www.google.com");
    System.out.println(urlVal3.isValid("h3tp://www.google.com"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http:/www.google.com");
    System.out.println(urlVal3.isValid("://www.google.com"));
    System.out.println("://www.google.com");
    System.out.println(urlVal3.isValid("://www.google.com"));

    // authority
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal3.isValid("http://www.google.com"));
    System.out.println("http://www.google.au");
    System.out.println(urlVal3.isValid("http://www.google.au"));
    // special, SHOULD allow at allow local
    System.out.println("http://hostname");
    System.out.println(urlVal3.isValid("http://hostname"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google~.com");
    System.out.println(urlVal3.isValid("http://www.google~.com"));
    System.out.println("http://www.google");
    System.out.println(urlVal3.isValid("http://www.google"));
    System.out.println("http://broke.hostname.com");
    System.out.println(urlVal3.isValid("http://broke.hostname.com"));
    System.out.println("http://1.2.3");
    System.out.println(urlVal3.isValid("http://1.2.3"));
    System.out.println("http://<blank>");
    System.out.println(urlVal3.isValid("http:// "));

    // port
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal3.isValid("http://www.google.com:80"));
    System.out.println("http://www.google.com:300");
    System.out.println(urlVal3.isValid("http://www.google.com:300"));
    System.out.println("http://www.google.com:3000");
    System.out.println(urlVal3.isValid("http://www.google.com:3000"));
    System.out.println("http://www.google.com:30000");
    System.out.println(urlVal3.isValid("http://www.google.com:30000"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google.com:");
    System.out.println(urlVal3.isValid("http://www.google.com:"));
    System.out.println("http://www.google.com:-80");
    System.out.println(urlVal3.isValid("http://www.google.com:-80a"));
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal3.isValid("http://www.google.com:80a"));

    // path
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1");
    System.out.println(urlVal3.isValid("http://www.google.com/test1"));
    System.out.println("http://www.google.com/");
    System.out.println(urlVal3.isValid("http://www.google.com/"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1");
    System.out.println(urlVal3.isValid("http://www.google.comtest1"));
    System.out.println("http://www.google.com//test1");
    System.out.println(urlVal3.isValid("http://www.google.com//test1"));
    System.out.println("http://www.google.com/..");
    System.out.println(urlVal3.isValid("http://www.google.com/.."));
    System.out.println("http://www.google.com/../");
    System.out.println(urlVal3.isValid("http://www.google.com/../"));

    // option
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1/test1");
    System.out.println(urlVal3.isValid("http://www.google.com/test1/test1"));
    System.out.println("http://www.google.com/test1/");
    System.out.println(urlVal3.isValid("http://www.google.com/test1/"));
    System.out.println("http://www.google.com/test1test1");
    System.out.println(urlVal3.isValid("http://www.google.com/test1test1"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1test1");
    System.out.println(urlVal3.isValid("http://www.google.comtest1test1"));
    System.out.println("http://www.google.com/test1//test1");
    System.out.println(urlVal3.isValid("http://www.google.com/test1//test1"));

    // queries
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com?action=view");
    System.out.println(urlVal3.isValid("http://www.google.com?action=view"));
    System.out.println("http://www.google.com?action=view&hi=hello");
    System.out.println(urlVal3.isValid("http://www.google.com?action=view&hi=hello"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.com?action");
    System.out.println(urlVal3.isValid("http://www.google.com?action="));
    System.out.println("http://www.google.com=view");
    System.out.println(urlVal3.isValid("http://www.google.comaction=view"));
    System.out.println("http://www.google.com??action=view");
    System.out.println(urlVal3.isValid("http://www.google.com??action=view"));

    UrlValidator urlVal4 = new UrlValidator(0);
    System.out.println("\nDefault: \n");
    // scheme
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal4.isValid("http://www.google.com"));
    System.out.println("www.google.com");
    System.out.println(urlVal4.isValid("www.google.com"));
    System.out.println("h3tp://www.google.com");
    System.out.println(urlVal4.isValid("h3tp://www.google.com"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http:/www.google.com");
    System.out.println(urlVal4.isValid("://www.google.com"));
    System.out.println("://www.google.com");
    System.out.println(urlVal4.isValid("://www.google.com"));

    // authority
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com");
    System.out.println(urlVal4.isValid("http://www.google.com"));
    System.out.println("http://www.google.au");
    System.out.println(urlVal4.isValid("http://www.google.au"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google~.com");
    System.out.println(urlVal4.isValid("http://www.google~.com"));
    System.out.println("http://www.google");
    System.out.println(urlVal4.isValid("http://www.google"));
    System.out.println("http://broke.hostname.com");
    System.out.println(urlVal4.isValid("http://broke.hostname.com"));
    System.out.println("http://1.2.3");
    System.out.println(urlVal4.isValid("http://1.2.3"));
    System.out.println("http://<blank>");
    System.out.println(urlVal4.isValid("http:// "));
    // special, should NOT allow at default
    System.out.println("http://hostname");
    System.out.println(urlVal4.isValid("http://hostname"));

    // port
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal4.isValid("http://www.google.com:80"));
    System.out.println("http://www.google.com:300");
    System.out.println(urlVal4.isValid("http://www.google.com:300"));
    System.out.println("http://www.google.com:3000");
    System.out.println(urlVal4.isValid("http://www.google.com:3000"));
    System.out.println("http://www.google.com:30000");
    System.out.println(urlVal4.isValid("http://www.google.com:30000"));
    System.out.println("\nShould Not Validate:");
    System.out.println("http://www.google.com:");
    System.out.println(urlVal4.isValid("http://www.google.com:"));
    System.out.println("http://www.google.com:-80");
    System.out.println(urlVal4.isValid("http://www.google.com:-80a"));
    System.out.println("http://www.google.com:80");
    System.out.println(urlVal4.isValid("http://www.google.com:80a"));

    // path
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1");
    System.out.println(urlVal4.isValid("http://www.google.com/test1"));
    System.out.println("http://www.google.com/");
    System.out.println(urlVal4.isValid("http://www.google.com/"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1");
    System.out.println(urlVal4.isValid("http://www.google.comtest1"));
    System.out.println("http://www.google.com//test1");
    System.out.println(urlVal4.isValid("http://www.google.com//test1"));
    System.out.println("http://www.google.com/..");
    System.out.println(urlVal4.isValid("http://www.google.com/.."));
    System.out.println("http://www.google.com/../");
    System.out.println(urlVal4.isValid("http://www.google.com/../"));

    // option
    System.out.println("\nShould Validate: ");
    System.out.println("http://www.google.com/test1/test1");
    System.out.println(urlVal4.isValid("http://www.google.com/test1/test1"));
    System.out.println("http://www.google.com/test1/");
    System.out.println(urlVal4.isValid("http://www.google.com/test1/"));
    System.out.println("http://www.google.com/test1test1");
    System.out.println(urlVal4.isValid("http://www.google.com/test1test1"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.comtest1test1");
    System.out.println(urlVal4.isValid("http://www.google.comtest1test1"));
    System.out.println("http://www.google.com/test1//test1");
    System.out.println(urlVal4.isValid("http://www.google.com/test1//test1"));

    // queries
    System.out.println("\nShould Validate:");
    System.out.println("http://www.google.com?action=view");
    System.out.println(urlVal4.isValid("http://www.google.com?action=view"));
    System.out.println("http://www.google.com?action=view&hi=hello");
    System.out.println(urlVal4.isValid("http://www.google.com?action=view&hi=hello"));
    System.out.println("\nShould Not Validate: ");
    System.out.println("http://www.google.com?action");
    System.out.println(urlVal4.isValid("http://www.google.com?action="));
    System.out.println("http://www.google.com=view");
    System.out.println(urlVal4.isValid("http://www.google.comaction=view"));
    System.out.println("http://www.google.com??action=view");
    System.out.println(urlVal4.isValid("http://www.google.com??action=view"));

  }
  
  // scheme partition
  public void testYourFirstPartition() {
    System.out.println("\nTesting Schemes:\n");

    String[] ourSchemes = {"http://", "h3tp://", "://", ":/", " "};
    UrlValidator schemeVal = new UrlValidator(ourSchemes, 0);
    for (int i = 0; i < ourSchemes.length; i++) {
      String curScheme = ourSchemes[i];
      System.out.println("\nTesting " + curScheme);
      boolean valid = schemeVal.isValidScheme(curScheme);
      if (valid == false && i == 0 || valid == false && i == 1 || valid == true && i == 2 || valid == true && i == 3 || valid == false && i == 4) {
        System.out.println("FAILED, invalid scheme\n");
      } else {
    	System.out.println("PASSED\n");
      }
    }
  }

  // authority partition
  public void testYourSecondPartition() {
    System.out.println("\nTesting Authority:\n");

    String[] ourAuthority = {"www.google.com", "www.google~.com", "www.google", " ", "broke.hostname.com"};
    UrlValidator authVal = new UrlValidator(ourAuthority, 0);
    for (int i = 0; i < ourAuthority.length; i++) {
      String curAuth = ourAuthority[i];
      System.out.println("\nTesting " + curAuth);
      boolean valid = authVal.isValidAuthority(curAuth);
      if (valid == false && i == 0 || valid == true && i == 1 || valid == true && i == 2 || valid == true && i == 3 || valid == true && i == 4) {
        System.out.println("FAILED, invalid authority\n");
      } else {
    	System.out.println("PASSED\n");
      }
    }
  }

  // port partition - Done with the isValidAuthority(String authority) function
  public void testYourThirdPartition() {
    System.out.println("\nTesting Port:\n");

    String[] ourPort = {"www.google.com:80", "www.google.com:", "www.google.com:-80", "www.google.com:80a"};
    UrlValidator portVal = new UrlValidator(ourPort, 0);
    for (int i = 0; i < ourPort.length; i++) {
      String curPort = ourPort[i];
      System.out.println("\nTesting " + curPort);
      boolean valid = portVal.isValidAuthority(curPort);
      if (valid == false && i == 0 || valid == true && i == 1 || valid == true && i == 2 || valid == true && i == 3) {
       System.out.println("FAILED, invalid port\n");
      } else {
    	System.out.println("PASSED\n");
      }
    }
  }

  // path Partition
  public void testYourFourthPartition(){
    System.out.println("\nTesting Path:\n");

    String[] ourPath = {"/test1", "//test1", "/", "test1", ".."};
    UrlValidator pathVal = new UrlValidator(ourPath, 0);
    for (int i = 0; i < ourPath.length; i++) {
      String curPath = ourPath[i];
      System.out.println("\nTesting " + curPath);
      boolean valid = pathVal.isValidPath(curPath);
      if (valid == false && i == 0 || valid == true && i == 1 || valid == false && i == 2 || valid == true && i == 3) {
        System.out.println("FAILED, invalid path\n");
      } else {
    	System.out.println("PASSED\n");
      }
    }
  }


  // query Partition
  public void testYourFifthPartition() {
    System.out.println("\nTesting Queries:\n");

    String[] ourQueries = {"?action=view", "?action=", "/", "??action=view"};
    UrlValidator pathQuery = new UrlValidator(ourQueries, 0);
    for (int i = 0; i < ourQueries.length; i++) {
      String curQuery = ourQueries[i];
      System.out.println("\nTesting " + curQuery);
      boolean valid = pathQuery.isValidQuery(curQuery);
      if(valid == false && i == 0 || valid == true && i == 1 || valid == false && i == 2 || valid == true && i == 3) {
        System.out.println("FAILED, invalid Query\n");
      } else {
    	System.out.println("PASSED\n");
      }
    }
  }


 
  /**
  * Create set of tests by taking the testUrlXXX arrays and
  * running through all possible permutations of their combinations.
  *
  * @param testObjects Used to create a url.
  */
public void testRandomURL() {
	
UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
  //Random Tests
  System.out.println("Random Testing:\n");
  
//create instance of Random class 
Random rand = new Random(); 

 
for (int i = 0; i < 10; i++) {
//randomize 1-8 three times into variables rdmStart, rdmMiddle, rdmEnd
int rdmStart = (rand.nextInt(8) +1);
int rdmMiddle = (rand.nextInt(8) +1);
int rdmEnd = (rand.nextInt(8) +1);

//Initialize Random String
String testURL = "";

//Start switch statement 
switch(rdmStart)
{
  case 1 : //Valid
     testURL = testURL+ "www.";
     break; 
  
  case 2 : //Valid
     testURL = testURL+ "google.";
     break; 

  case 3 : //Valid
     testURL = testURL+ "things.";
     break; 
  
  case 4 : //Valid
     testURL = testURL+ "support.";
     break;    

  case 5 : //Invalid
     testURL = testURL+ "w w w.";
     break; 
  
  case 6 : //invalid
     testURL = testURL+ "$$$.";
     break; 

  case 7 : //invalid
     testURL = testURL+ "~things";
     break; 
  
  case 8 : //Invalid
     testURL = testURL+ "supp ort.";
     break;    
//Default if random value is out of range
  default : 
     System.out.println ("Random value out of range: rdmStart = ");
     System.out.println (rdmStart);
     System.out.println ("\n");

}

//Middle switch statement 
switch(rdmMiddle)
{
  case 1 : //Valid
     // Nothing added here. Should be valid
     break; 
  
  case 2 : //Valid
     testURL = testURL+ "website.";
     break; 

  case 3 : //Valid
     testURL = testURL+ "thislongerstringshouldbeperfectlyvalid.";
     break; 
  
  case 4 : //Valid
     testURL = testURL+ "NOBODYCARESABOUTCAPS.";
     break;    

  case 5 : //Invalid
     testURL = testURL+ "stu ff.";
     break; 
  
  case 6 : //invalid
     testURL = testURL+ "dumb\\" ;
     break; 

  case 7 : //invalid
     testURL = testURL+ ">>>>";
     break; 
  
  case 8 : //Invalid
     testURL = testURL+ "\\\\\\\\";
     break;    
//Default if random value is out of range
  default : 
     System.out.println ("Random value out of range: rdmMiddle = ");
     System.out.println (rdmMiddle);
     System.out.println ("\n");
}

//End switch statement 
switch(rdmEnd)
{
  case 1 : //Valid
     testURL = testURL+ "com";
     break; 
  
  case 2 : //Valid
     testURL = testURL+ "eu";
     break; 

  case 3 : //Valid
     testURL = testURL+ "us";
     break; 
  
  case 4 : //Valid
     testURL = testURL+ "net";
     break;    

  case 5 : //Invalid
     testURL = testURL+ "bro-ken";
     break; 
  
  case 6 : //invalid
     testURL = testURL+ "not working";
     break; 

  case 7 : //invalid
     // no ending text;
     break; 
  
  case 8 : //Invalid
     testURL = testURL+ ">>>";
     break;    
//Default if random value is out of range
  default : 
     System.out.println ("Random value out of range: rdmStart = " );
     System.out.println (rdmStart);
     System.out.println ("\n");

}

System.out.println ("Complete test String: ");
System.out.println (testURL);
if (rdmStart > 4 || rdmMiddle > 4 || rdmEnd > 4){
   System.out.println ("URL Should Return INVALID");
} else {
   System.out.println ("URL Should Return VALID");
}

//Plug testURL into Validator here
   System.out.println(urlVal.isValid(testURL));
   System.out.println ("\n");

}
}
  
}
