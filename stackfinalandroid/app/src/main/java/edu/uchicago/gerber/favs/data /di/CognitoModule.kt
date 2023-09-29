package edu.uchicago.gerber.favs.data.di


import android.content.Context
import com.amazonaws.ClientConfiguration
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool
import com.amazonaws.regions.Regions
import edu.uchicago.gerber.favs.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CognitoModule {


    @Provides
    @Singleton
    fun providesCognitoUserPool(
        @ApplicationContext context:Context
    ):CognitoUserPool =

        CognitoUserPool(
            context,
            context.resources.getString(R.string.user_pool_id),
            context.resources.getString(R.string.client_id),
            null,
            ClientConfiguration(),
            Regions.US_EAST_1
        )


}