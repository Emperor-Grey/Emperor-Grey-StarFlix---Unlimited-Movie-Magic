package com.king_grey.movie_app.screens.discover.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.king_grey.movie_app.screens.discover.data.local.dao.MovieDao
import com.king_grey.movie_app.screens.discover.data.local.dao.TvShowDao
import com.king_grey.movie_app.screens.discover.data.local.dao.mediator.MovieRemoteKeyDao
import com.king_grey.movie_app.screens.discover.data.local.dao.mediator.TvShowRemoteKeyDao
import com.king_grey.movie_app.screens.discover.domain.model.entities.MovieEntity
import com.king_grey.movie_app.screens.discover.domain.model.entities.TvShowEntity
import com.king_grey.movie_app.screens.discover.domain.model.movie.MovieRemoteKey
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShowRemoteKey

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, MovieRemoteKey::class, TvShowRemoteKey::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class TMDbDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    abstract fun movieRemoteKeyDao(): MovieRemoteKeyDao
    abstract fun tvShowRemoteKeyDao(): TvShowRemoteKeyDao

}
