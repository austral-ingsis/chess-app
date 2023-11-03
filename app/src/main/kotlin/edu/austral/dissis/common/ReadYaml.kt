package edu.austral.dissis.common

import edu.austral.dissis.common.piece.Piece

interface ReadYaml {
    fun readInitialPositions(fileName: String, pieceTypes: List<String>): Map<Position, Piece>
}