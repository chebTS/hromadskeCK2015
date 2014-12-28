package com.hromadske.tv.ck.utils;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import static org.codehaus.jackson.map.DeserializationConfig.*;

/**
 * Created by cheb on 12/27/14.
 */
public class SystemUtils {
        private static final String BASE_URL = "http://hromadske.cherkasy.ua/?option=com_hromadskeapi";
        public static final String NEWS_URL = BASE_URL + "&category=news";
        public static final String POLITICS_URL = BASE_URL + "&category=polityka";
        public static final String SOCIETY_URL = BASE_URL + "&category=suspilstvo";
        public static final String CULTURE_URL = BASE_URL + "&category=kultura";
        public static final String KINO_URL = BASE_URL + "&category=kinodoc";
        public static final String PHOTO_URL = BASE_URL + "&category=fotodoc";
        public static final String TEAM_URL = BASE_URL + "&category=team";

    public static final String EXTRA_ENTITY = "entity";
    public static ImageLoader IMAGELOADER = null;
    public final static ObjectMapper MAPPER = new ObjectMapper().configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);


}
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=news
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=polityka
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=suspilstvo
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=kultura
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=kinodoc
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=fotodoc
//        http://hromadske.cherkasy.ua/?option=com_hromadskeapi&category=team
//


