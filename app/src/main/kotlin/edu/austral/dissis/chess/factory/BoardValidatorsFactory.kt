package edu.austral.dissis.chess.factory

import checkers.PriorityMovement
import chess.program.src.boardValidator.*
import common.enums.Color
import common.enums.Type
import common.movement2.DiagonalMovement
import common.movement2.HorizontalAndVerticalMovement
import common.movement2.strategyMovement.DiagonalObligatoryTrepassin
import common.movement2.strategyMovement.NoEatStrategy

class BoardValidatorsFactory {

    fun createChessValidators(): List<Validator> {
        val occupiedValidator = OccupiedByTeamPiece()
        val tipos = ArrayList<Type>()
        tipos.add(Type.FIRSTPAWN)
        tipos.add(Type.PAWN)

        val tipos1 = ArrayList<Type>()
        tipos1.add(Type.FRSTKING)
        tipos1.add(Type.KING)
        val cantBeEaten = cantBeEaten(tipos1)

        val moveIfEat = MoveIfEat(tipos, DiagonalMovement(1, 1, 1, 1))
        val moveIfNotEat = MoveIfNotEat(tipos, HorizontalAndVerticalMovement(1, 1, 1, 1))
        val checkValidator = CheckValidator()
        val boardValidators = ArrayList<Validator>()
        boardValidators.add(checkValidator)
        boardValidators.add(moveIfEat)
        boardValidators.add(moveIfNotEat)
        boardValidators.add(occupiedValidator)
        boardValidators.add(cantBeEaten)

        return boardValidators
    }

    fun createCheckersValidators(): List<Validator> {
        val priorityMovement = PriorityMovement(DiagonalMovement(2,2,2,0, listOf( DiagonalObligatoryTrepassin(), NoEatStrategy())), Color.WHITE)
        val priorityMovement1 = PriorityMovement(DiagonalMovement(2,2,0,2,listOf( DiagonalObligatoryTrepassin(), NoEatStrategy())), Color.BLACK)
        val boardValidators = ArrayList<Validator>()
        boardValidators.add(priorityMovement)
        boardValidators.add(priorityMovement1)

        return boardValidators
    }

}