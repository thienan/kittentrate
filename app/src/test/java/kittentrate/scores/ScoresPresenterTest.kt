package kittentrate.scores

import kittentrate.RobolectricTestHelper
import kittentrate.data.repository.GameRepository
import kittentrate.score.ScoresContract
import kittentrate.score.ScoresPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

/**
 * Created by Manuel Lorenzo
 */
@RunWith(RobolectricTestRunner::class)
class ScoresPresenterTest : RobolectricTestHelper() {
    @Mock
    private lateinit var view: ScoresContract.View
    @Mock
    private lateinit var repository: GameRepository

    private lateinit var scoresPresenter: ScoresPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scoresPresenter = ScoresPresenter(view, repository)
    }

    @Test
    fun `ScoresPresenter should initialize itself by fetching the top scores from the repo`() {
        scoresPresenter.start()

        verify(repository).topScores
    }
}