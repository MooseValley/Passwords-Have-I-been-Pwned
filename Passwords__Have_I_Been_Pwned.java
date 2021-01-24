/*
* Source:  Passwords__Have_I_Been_Pwned.java
* Author:  Mike "Moose" OMalley
* Desc:    See "README.MD" file for further information, how to use,
           how to compile, how to run, sample output, etc.

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuffer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Passwords__Have_I_Been_Pwned
{
   // Have I Been Pwned?  (HIBP)
   private static String HAVE_I_BEEN_OWNED_WEB_SITE = "https://api.pwnedpasswords.com/";

   // Passwords to check:
   // Edit this array - add passwords, remove passwords, etc.
   // Enter your passwords inside double quotes and comma separated.
   private static String[] PASSWORDS_ARRAY
      = {"abc123", "P@ssw0rd", "password", "password1", "p@ssword", "abcd1234.zxc"};



   // Copied and simplified / reduced from my: Checksum.java
   private static String calculateSHA1ForString (String inString)
   {
      MessageDigest md    = null;
      String checksumStr  = "0";

      try
      {
         md = MessageDigest.getInstance ("SHA-1");
         md.update (inString.getBytes());

         StringBuffer hexStringStringBuffer1 = new StringBuffer();
         byte[] mdbytes = md.digest();

         for (int i = 0; i < mdbytes.length; i++)
         {
            hexStringStringBuffer1.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
         }

         checksumStr = hexStringStringBuffer1.toString();
      }
      catch (NoSuchAlgorithmException err)
      {
         //err.printStackTrace();
         checksumStr = "ERROR: " + err.getMessage();
         System.out.println (checksumStr);
      }

      return checksumStr;
   }


   private static ArrayList<String> sendGet(String host, String apiEndpoint) throws Exception
   {
      ArrayList<String> resultsArrayList = new ArrayList<String> ();

      URL obj = new URL(host+apiEndpoint);
      int lineCount = 0;

      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      // optional default is GET
      con.setRequestMethod("GET");

      //add request header
      con.setRequestProperty("User-Agent", ""); //USER_AGENT);

      int responseCode = con.getResponseCode();
      //System.out.println("Server Response Code: " + responseCode);

      BufferedReader in = new BufferedReader
          (new InputStreamReader(con.getInputStream()));
      String inputLine;
      //StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null)
      {
         lineCount++;
         if (lineCount == 1)
         {
            // The first line has 3 characters of junk, so let's truncate.
            inputLine = inputLine.substring (3, inputLine.length());
         }
         //response.append(inputLine);
         resultsArrayList.add (inputLine);

         //System.out.println (String.format ("%03d", lineCount) + ": " + inputLine);
      }
      in.close();

      //return (response.toString());
      return resultsArrayList;
   }

   public static void listPasswordHashes (ArrayList<String> sha1PasswordResultsArrayList, int maxToShow)
   {
      if (maxToShow == 0)
      {
         // Show all.
         maxToShow = sha1PasswordResultsArrayList.size();
      }

      for  (int k = 0; k < maxToShow; k++)
      {
         System.out.println (sha1PasswordResultsArrayList.get(k));
      }
   }

   public static boolean isMyPasswordPwned (String sha1Last35CharsStr,  ArrayList<String> sha1PasswordResultsArrayList)
   {
      boolean result = false;

      // Compare last 35 characters of my password's SHA1
      // against the SHA1's returned from HIBP.
      for  (int k = 0; k < sha1PasswordResultsArrayList.size(); k++)
      {
         if (sha1PasswordResultsArrayList.get(k).contains (sha1Last35CharsStr) == true)
         {
            result = true;
         }
      }

      return result;
   }


   public static void main (String[] args)
   {
      ArrayList<String> sha1PasswordResultsArrayList  = new ArrayList<String> ();

      String  sha1EncodedPasswordStr = "";
      String  sha1First5CharsStr     = "";
      String  sha1Last35CharsStr     = "";
      String  passwordStr            = "";
      int     ownedCount             = 0;
      boolean ownedResult            = false;

      System.out.println ("---------------------------------------");
      System.out.println ("Have I Been Pwned Password Check: ");
      System.out.println ("---------------------------------------");
      System.out.println ();

      System.out.println (" ID  " +
                          String.format ("%-7s",  "Pwned?") +
                          String.format ("%-20s", "Password"));

      try
      {
         for (int k = 0; k < PASSWORDS_ARRAY.length; k++)
         {
            passwordStr = PASSWORDS_ARRAY [k];
            //sha1EncodedPasswordStr = Checksum.calculateChecksumForString ("SHA-1", passwordStr);
            sha1EncodedPasswordStr = calculateSHA1ForString (passwordStr);

            // The SHA1's *MUST* be uppercase, otherwise his web site does NOT find them.
            sha1EncodedPasswordStr = sha1EncodedPasswordStr.toUpperCase();
            sha1First5CharsStr     = sha1EncodedPasswordStr.substring (0, 5);
            sha1Last35CharsStr     = sha1EncodedPasswordStr.substring (5, 40);
            ownedResult            = false;

            sha1PasswordResultsArrayList = sendGet (HAVE_I_BEEN_OWNED_WEB_SITE +
                                                    "range/" + sha1First5CharsStr, "");

            ownedResult = isMyPasswordPwned (sha1Last35CharsStr, sha1PasswordResultsArrayList);

            //System.out.println ("Password " + (k+1) + ":       " + passwordStr);
            //System.out.println ("-> Password Hash:   " + sha1EncodedPasswordStr);
            //System.out.println ("-> PW 1st 5 chars:  " + sha1First5CharsStr);
            //System.out.println ("-> Last 35 chars:   " + sha1Last35CharsStr);
            //System.out.println ("-> Partial Matches: " + sha1PasswordResultsArrayList.size());
            //System.out.println ("-> Full Match:      " + ownedResult);
            //System.out.println ();

            System.out.println (String.format ("%3d", k+1) + ". " +
                          String.format ("%-7s",  (ownedResult == true) ? "  Y" : "  N" ) +
                          String.format ("%-60s", passwordStr));

            /*
            if (k == 0)
            {
               System.out.println ("-> Password Hash:   " + sha1EncodedPasswordStr);
               System.out.println ("-> PW 1st 5 chars:  " + sha1First5CharsStr);
               System.out.println ("-> Last 35 chars:   " + sha1Last35CharsStr);
               System.out.println ("-> Partial Matches: " + sha1PasswordResultsArrayList.size());
               listPasswordHashes (sha1PasswordResultsArrayList, 5);
               System.out.println ();
            }
            */

            if (ownedResult == true)
            {
               ownedCount++;
            }
         }

         if (ownedCount > 0)
         {
            System.out.println ("\n*** WARNING: YOU HAVE BEEN PWNED " + ownedCount + " times !!! :(");
         }
         else
         {
            System.out.println ("\n*** ALL OK - nothing to worry about.  :)");
         }
         System.out.println ();

      }
      catch (Exception err)
      {
         err.printStackTrace();
      }
   }
}
