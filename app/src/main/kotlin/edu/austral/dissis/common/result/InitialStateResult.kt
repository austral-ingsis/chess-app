package edu.austral.dissis.common.result

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.turnStrategy.TurnStrategy

data class InitialStateResult(val firstTurn: TurnStrategy, val board: Board )