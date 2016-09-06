# Movies

Android Application to fetch Most popular/Top Rated movies. 

User can also favourite his movies.

In this I have used tmdb api to fetch the movie information.

I have used latest Android features to develop this application and emphasis is given on coding efficiency, responsive ui.

Application is designed and optimised for both mobile and tablet android devices.


For tmdb movie api key - Goto build.gradle under the app

buildTypes.each {
        it.buildConfigField 'String', 'MOVIE_API_KEY', "\"movies api key\""
    }
    
    Replace your key with movies api key
    For example if your api key is - 1234
   buildTypes.each {
        it.buildConfigField 'String', 'MOVIE_API_KEY', "\"1234\""
    } 
