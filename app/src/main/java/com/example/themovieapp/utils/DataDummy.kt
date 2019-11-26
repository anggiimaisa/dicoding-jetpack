package com.example.themovieapp.utils

import com.example.themovieapp.R
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow

class DataDummy {

    companion object {
        fun getMovieDatas() : List<Movie> {
            var movies : ArrayList<Movie> = ArrayList()

            movies.add(Movie(1, "Adventure Time", "There are fin and his friend yipii...", R.drawable.adventuretime))
            movies.add(Movie(2, "Ben 10", "I really want his watch",  R.drawable.ben10))
            movies.add(Movie(3, "Dora the explorer", "Where is the mountain? Over there dora.....", R.drawable.doretheexplorer))
            movies.add(Movie(4, "Gravity Falls", "Where are we?", R.drawable.gravfalls))
            movies.add(Movie(5, "Mulan", "Watch me on your theater", R.drawable.mulan))
            movies.add(Movie(6, "Finding Nemo", "Dori where is nemo? Ahhh I forgot", R.drawable.nemo))
            movies.add(Movie(7, "Winnie the Pooh", "Can I get more honey?",  R.drawable.pooh))
            movies.add(Movie(8, "The Amazing World of Gumball", "Hey Gumball", R.drawable.theamazingworldofgumball))
            movies.add(Movie(9, "Tom and Jerry", "Hey Tom watch your steps", R.drawable.tomandjerry))
            movies.add(Movie(10, "We Bare Bear", "I want to eat", R.drawable.wbb))

            return movies
        }

        fun getTVShowDatas() : List<TVShow> {
            var tvShow : ArrayList<TVShow> = ArrayList()

            tvShow.add(TVShow(1, "Arrow",
                "When presumed-dead billionaire playboy Oliver Queen returns home to Starling City after five years stranded on a remote island in the Pacific, he hides the changes the experience had on him, while secretly seeking reconciliation with his ex, Laurel. By day he picks up where he left off, playing the carefree philanderer he used to be, but at night he dons the alter ego of Arrow and works to right the wrongs of his family and restore the city to its former glory.",
                R.drawable.arrow))
            tvShow.add(TVShow(2, "Black Mirror",
                "DescriptionFeaturing stand-alone dramas -- sharp, suspenseful, satirical tales that explore techno-paranoia -- \"Black Mirror\" is a contemporary reworking of \"The Twilight Zone\" with stories that tap into the collective unease about the modern world.",
                R.drawable.black_mirror))
            tvShow.add(TVShow(3, "Doctor Who",
                "An eccentric yet compassionate extraterrestrial Time Lord zips through time and space to solve problems and battle injustice across the universe, traveling via the TARDIS (Time and Relative Dimensions in Space), which is his old and occasionally unreliable spaceship that resembles a blue police phone box (but changes its appearance depending on its surroundings) and is much, much larger inside than outside.",
                R.drawable.doctor_who))
            tvShow.add(TVShow(4, "Good Doctor",
                "Shaun Murphy, a young autistic surgeon who has savant syndrome, relocates from a quiet country life to join the surgical unit at the prestigious San Jose St. Bonaventure Hospital -- a move strongly supported by his mentor, Dr. Aaron Glassman.",
                R.drawable.good_doctor))
            tvShow.add(TVShow(5, "Game of Thrones",
                "George R.R. Martin's best-selling book series \"A Song of Ice and Fire\" is brought to the screen as HBO sinks its considerable storytelling teeth into the medieval fantasy epic. It's the depiction of two powerful families -- kings and queens, knights and renegades, liars and honest men -- playing a deadly game for control of the Seven Kingdoms of Westeros, and to sit atop the Iron Throne.",
                R.drawable.got))
            tvShow.add(TVShow(6, "Silicon Valley",
                "Partially inspired by co-creator Mike Judge's experiences as a Silicon Valley engineer in the 1980s, this comedy series follows the misadventures of introverted computer programmer Richard and his brainy friends as they attempt to strike it rich in a high-tech gold rush.",
                R.drawable.sillicon_valley))
            tvShow.add(TVShow(7, "Stranger Things",
                "In 1980s Indiana, a group of young friends witness supernatural forces and secret government exploits. As they search for answers, the children unravel a series of extraordinary mysteries.",
                R.drawable.stranger_things))
            tvShow.add(TVShow(8, "The Crown",
                "Based on an award-winning play (\"The Audience\") by showrunner Peter Morgan, this lavish, Netflix-original drama chronicles the life of Queen Elizabeth II (Claire Foy) from the 1940s to modern times.",
                R.drawable.the_crown))
            tvShow.add(TVShow(9, "The Flash",
                "At 11, Barry Allen's life changed completely when his mother died in a freak accident and his innocent father was convicted of her murder. Orphaned Barry later becomes Detective Joe West.",
                R.drawable.the_flash))
            tvShow.add(TVShow(10, "The Gifted",
                "Marvel expands its footprint on the television landscape with this new family adventure series about an ordinary suburban family whose lives change course forever when they discover their children have developed mutant powers.",
                R.drawable.the_gifted))

            return tvShow
        }
    }

}