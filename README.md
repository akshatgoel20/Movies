# Movies

Android Application to fetch Most popular/Top Rated movies.

For tmdb movie api key - Goto build.gradle under the app

buildTypes.each {
        it.buildConfigField 'String', 'MOVIE_API_KEY', "\"movies api key\""
    }
    
    Replace your key with movies api key
    For example if your api key is - 1234
   buildTypes.each {
        it.buildConfigField 'String', 'MOVIE_API_KEY', "\"1234\""
    } 
