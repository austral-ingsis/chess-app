package edu.austral.dissis.mychess.validator

import BoardFactory
import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor
import java.lang.RuntimeException

class CheckMateValidator {
    private val kingInCheckValidator = KingInCheckValidator()
    fun validateMovement(board: Board, kingPosition: Position, kingColor: PieceColor, movement: Movement): Boolean {
        if (!kingInCheckValidator.isKingInCheck(board, kingPosition, kingColor)){
            return false // El rey no está en jaque, por lo que no puede haber jaque mate.
        }
        val playerPieces : List<Piece> = board.getPiecesPositions().values.filter { it.getPieceColor() == kingColor }
        for (playerPiece in playerPieces){
            val piecePosition = board.getPiecesPositions().entries.find { it.value == playerPiece }?.key
            if (piecePosition != null){
                for (rule in playerPiece.getRuleList()){
                    val targetPosition = movement.finalPosition
                    if (rule.isValidRule(board, Movement(playerPiece, targetPosition))::class.simpleName == "SuccessfulRuleResult"){
                        val simulatedMove = simulateMoveForCheckmate(board, playerPiece, piecePosition, targetPosition)
                        val newKingPosition = findKingPosition(simulatedMove, kingColor)
                        if (!kingInCheckValidator.isKingInCheck(simulatedMove, newKingPosition, kingColor)){
                            return false
                        }
                    }
                }
            }
//            for (pieceRule in playerPiece.getRuleList()){
//                if (pieceRule.isValidRule(board, Movement(playerPiece, movement.finalPosition))::class.simpleName == "SuccessfulRuleResult"){
//                    val newKingPosition : Position = movement.finalPosition
//                    if (!kingInCheckValidator.isKingInCheck(board, newKingPosition, kingColor)){
//                        return false // Existe un movimiento legal que evita el jaque mate
//                    }
//                }
//            }
        }

        return true
    }

    private fun simulateMoveForCheckmate(board: Board, piece: Piece, sourcePosition: Position, targetPosition: Position): Board{
        val newPiecesPositions : MutableMap<Position, Piece> = board.getPiecesPositions().toMutableMap()

        newPiecesPositions.remove(sourcePosition)
        newPiecesPositions[targetPosition] = piece

        return BoardFactory.createNewClassicBoard(newPiecesPositions, board)
    }

    private fun findKingPosition(board: Board, kingColor: PieceColor) : Position{
        for (piece in board.getPiecesPositions().values){
            if (piece::class.simpleName == "King" && piece.getPieceColor() == kingColor){
                return board.getPositionByPiece(piece)
            }
        }
        throw RuntimeException("")
    }

}