package kuplrg

/** The definition of deterministic finite automaton (DFA)
  *
  * @constructor
  *   create a new DFA
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
case class DFA(
  states: Set[State],
  symbols: Set[Symbol],
  trans: Map[(State, Symbol), State],
  initState: State,
  finalStates: Set[State],
) extends FA:

  /** The safe read of the transition function of DFA */
  def getTrans(q: State, a: Symbol): State =
    trans.getOrElse((q, a), error(s"Undefined transition: ($q, $a)"))

  /** The extended transition function of DFA
    *
    * @param q
    *   the current state
    * @param w
    *   the current word
    * @return
    *   the next state
    */
  def extTrans(q: State, w: Word): State = w match
    case ""     => q
    case x <| w => extTrans(getTrans(q, x), w)

  /** The acceptance of a word by DFA
    *
    * @param w
    *   the word to be checked for acceptance
    * @return
    *   `true` if the word is accepted, `false` otherwise
    */
  def accept(w: Word): Boolean =
    finalStates.contains(extTrans(initState, w))

object DFA:
  /** Create a new DFA from transition pairs (varargs)
    *
    * Checks for:
    *   - Duplicate transitions: same (state, symbol) with different targets
    *   - Exhaustiveness: all (state, symbol) pairs must be defined
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
    *   a new DFA
    */
  def apply(
    states: Set[State],
    symbols: Set[Symbol],
    initState: State,
    finalStates: Set[State],
  )(pairs: ((State, Symbol), State)*): DFA =
    val grouped = pairs.groupBy(_._1)
    // Check for duplicate (state, symbol) pairs
    for ((key, values) <- grouped if values.size > 1)
      val targets = values.map(_._2).distinct
      val msg = s"Duplicate transition for $key"
      if (targets.size > 1)
        error(s"$msg with different targets: ${targets.mkString(", ")}")
      else error(msg)
    // Check exhaustiveness
    val missing = for {
      q <- states
      a <- symbols
      if !grouped.contains((q, a))
    } yield (q, a)
    if (missing.nonEmpty)
      error(s"Missing transitions: ${missing.mkString(", ")}")
    val trans = grouped.map { case (key, values) => key -> values.head._2 }
    DFA(states, symbols, trans, initState, finalStates)
