package kuplrg

/** The definition of non-deterministic finite automaton (NFA)
  *
  * @constructor
  *   create a new NFA
  *
  * @param states
  *   the set of states
  * @param symbols
  *   the set of symbols
  * @param trans
  *   the transition function
  * @param initState
  *   the initial state
  * @param finalStates
  *   the set of final states
  */
case class NFA(
  states: Set[State],
  symbols: Set[Symbol],
  trans: Map[(State, Symbol), Set[State]],
  initState: State,
  finalStates: Set[State],
) extends FA:

  /** The safe read of the transition function of NFA */
  def getTrans(q: State, a: Symbol): Set[State] =
    trans.getOrElse((q, a), Set.empty)

  /** The extended transition function of NFA
    *
    * @param qs
    *   the current set of states
    * @param w
    *   the current word
    * @return
    *   the set of next possible states
    */
  def extTrans(qs: Set[State], w: Word): Set[State] = w match
    case ""     => qs
    case x <| w => extTrans(qs.flatMap(q => getTrans(q, x)), w)

  /** The acceptance of a word by NFA
    *
    * @param w
    *   the word to be checked for acceptance
    * @return
    *   `true` if the word is accepted, `false` otherwise
    */
  def accept(w: Word): Boolean =
    extTrans(Set(initState), w).intersect(finalStates).nonEmpty

object NFA:
  /** Create a new NFA from transition pairs (varargs)
    *
    * Automatically merges duplicate (state, symbol) pairs into a Set of target
    * states.
    *
    * @param states
    *   the set of states
    * @param symbols
    *   the set of symbols
    * @param initState
    *   the initial state
    * @param finalStates
    *   the set of final states
    * @param pairs
    *   the transition pairs
    * @return
    *   a new NFA
    */
  def apply(
    states: Set[State],
    symbols: Set[Symbol],
    initState: State,
    finalStates: Set[State],
  )(pairs: ((State, Symbol), State)*): NFA =
    val trans = pairs.groupBy(_._1).map {
      case (key, values) => key -> values.map(_._2).toSet
    }
    NFA(states, symbols, trans, initState, finalStates)
