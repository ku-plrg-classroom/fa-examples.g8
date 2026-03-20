package kuplrg

object Implementation extends Template {

  /** This is the playground for you to run your implementation. Do whatever you
    * want here and run `sbt run` to see the result.
    */
  @main def playground: Unit = {
    println("------------------- PLAYGROUND -------------------")

    // You can check your implementation here.
    println("dfa_waa.accept(aa)  = " + dfa_waa.accept("aa"))
    println("dfa_waa.accept(ab)  = " + dfa_waa.accept("ab"))
    println("dfa_waa.accept(baa) = " + dfa_waa.accept("baa"))

    println("--------------------------------------------------")

    // You can dump the DFA to see it in the automaton viewer.
    // After running this program, open `viewer/index.html` in your browser.
    dfa_waa.dump

    println("--------------------------------------------------")
  }

  // ---------------------------------------------------------------------------
  // Deterministic Finite Automata (DFA)
  // ---------------------------------------------------------------------------
  // Example DFA for L = { w a a | w \in {a, b}* }
  def dfa_waa: DFA = DFA(
    states = Set(0, 1, 2),
    symbols = Set('a', 'b'),
    initState = 0,
    finalStates = Set(2),
  )(
    (0, 'a') -> 1,
    (0, 'b') -> 0,
    (1, 'a') -> 2,
    (1, 'b') -> 0,
    (2, 'a') -> 2,
    (2, 'b') -> 0,
  )

  // (Problem #1) DFA for L = { w \in {a, b}* | w has length divisible by 3 }
  def dfa_len_div_3: DFA = ???

  // (Problem #2) DFA for L = { w \in {a, b}+ | w starts and ends with
  // the same symbol }
  def dfa_same_start_end: DFA = ???

  // (Problem #3) DFA for L = { w \in {0, 1}* | w does NOT contain "101"
  // as a substring }
  def dfa_not_substr_101: DFA = ???

  // (Problem #4) DFA for L = { w \in {1, 2, 3}+ | w \equiv 1 (mod 4) }
  // where w is interpreted as a number in base 10
  def dfa_div_4_1: DFA = ???

  // ---------------------------------------------------------------------------
  // Nondeterministic Finite Automata (NFA)
  // ---------------------------------------------------------------------------
  // Example NFA for L = { w \in {0, 1}* | w contains at least two 0's }
  def nfa_least_two_0: NFA = NFA(
    states = Set(0, 1, 2),
    symbols = Set('0', '1'),
    initState = 0,
    finalStates = Set(2),
  )(
    (0, '0') -> 0,
    (0, '0') -> 1,
    (0, '1') -> 0,
    (1, '0') -> 1,
    (1, '0') -> 2,
    (1, '1') -> 1,
    (2, '0') -> 2,
    (2, '1') -> 2,
  )

  // (Problem #5) NFA for L = { w \in {a, b}* | w is any combination of aaa
  // and bb }
  // NOTE: You MUST implement it using 5 or fewer transitions
  def nfa_comb_aaa_bb: NFA = ???

  // (Problem #6) NFA for L = { w \in {0, 1}* | w ends with 101 or 110 }
  // NOTE: You MUST implement it using 7 or fewer transitions
  def nfa_101_or_110: NFA = ???

  // (Problem #7) NFA for L = { w \in {a, b}* | w contains aa or bb
  // as a substring }
  // NOTE: You MUST implement it using 8 or fewer transitions
  def nfa_has_aa_or_bb: NFA = ???

  // ---------------------------------------------------------------------------
  // epsilon-Non-deterministic Finite Automata (epsilon-NFA)
  // ---------------------------------------------------------------------------
  // Example ENFA for L = { a^i b^j c^k | i, j, k >= 0 }
  def enfa_ai_bj_ck: ENFA = ENFA(
    states = Set(0, 1, 2),
    symbols = Set('a', 'b', 'c'),
    initState = 0,
    finalStates = Set(2),
  )(
    (0, EPS) -> 1,
    (0, 'a') -> 0,
    (1, EPS) -> 2,
    (1, 'b') -> 1,
    (2, 'c') -> 2,
  )

  // (Problem #8) ENFA for L = { \eps, aa } { c^n | n >= 0 } { \eps, bb }
  // NOTE: You MUST implement it using 7 or fewer transitions
  def enfa_opt_pre_post: ENFA = ???

  // (Problem #9) ENFA for L = { aca, acb, bca, bcb }^+
  // NOTE: You MUST implement it using 6 or fewer transitions
  def enfa_some_plus: ENFA = ???

  // (Problem #10) ENFA for L = L_1 \cup L_2
  // where
  // L_1 = { w \in {0, 1} | N(w) \equiv 1 (mod 3) }
  // L_2 = { w \in {0, 1} | zeros(w) \equiv 1 (mod 3) }
  // N(w) is the natural number represented by w in binary,
  // and zeros(w) is the number of 0's in w
  // NOTE: You MUST implement it using 14 or fewer transitions
  def enfa_complex: ENFA = ???
}
