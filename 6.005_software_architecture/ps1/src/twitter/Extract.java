/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.*;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        
        Instant beginning = tweets.get(0).getTimestamp();
        Instant end = tweets.get(0).getTimestamp();
        
        for (Tweet post : tweets) {
            if (post.getTimestamp().isBefore(beginning)) {
                beginning = post.getTimestamp();
            } else if (post.getTimestamp().isAfter(end)) {
                end = post.getTimestamp();
            } else {
                continue;
            }
        }
        
        Timespan range = new Timespan(beginning, end);
        
        return range;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
       Set<String> mentionedUsers = new HashSet<String>();
       String userText;
       String[] splitText;
       
       for (Tweet post : tweets) {
           userText = post.getText();
           splitText = userText.split(" ", -1);
           
           for (String tag : splitText) {
               if (tag.substring(0,1) == "@") {
                   tag = tag.substring(1,tag.length());
                   mentionedUsers.add(tag);
                   System.out.println(tag);
               }
           }
       }
       
       return mentionedUsers;
    }

}
