sealed class MeditationDetailState {
    data object Loading : MeditationDetailState()
    data class Success(val meditation: MeditationDetail) : MeditationDetailState()
    data class Error(val message: String) : MeditationDetailState()
} 