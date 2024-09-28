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

  // DFA for L = { w a a | w \in {a, b}* }
  def dfa_waa: DFA = DFA(
    states = Set(0, 1, 2),
    symbols = Set('a', 'b'),
    trans = Map(
      (0, 'a') -> 1,
      (0, 'b') -> 0,
      (1, 'a') -> 2,
      (1, 'b') -> 0,
      (2, 'a') -> 2,
      (2, 'b') -> 0,
    ),
    initState = 0,
    finalStates = Set(2),
  )

  // DFA for L = { a b^n | n >= 0 }
  def dfa_a_b_star: DFA = DFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // DFA for L = { w \in {0, 1}* | N(w) \equiv 1 (mod 3) }
  // where N(w) is the natural number represented by w in binary
  def dfa_div_3_1: DFA = DFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // DFA for L = { w \in {0, 1}* | w contains a subsequence 101 }
  def dfa_subseq_101: DFA = DFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // DFA for L = { w \in {0, 1}* | w contains odd number of 0's
  // and odd number of 1's }
  def dfa_odd_0_1: DFA = DFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // NFA for L = { w \in {0, 1}* | w contains at least two 0's }
  def nfa_least_two_0: NFA = NFA(
    states = Set(0, 1, 2),
    symbols = Set('0', '1'),
    trans = Map(
      (0, '0') -> Set(0, 1),
      (0, '1') -> Set(0),
      (1, '0') -> Set(1, 2),
      (1, '1') -> Set(1),
      (2, '0') -> Set(2),
      (2, '1') -> Set(2),
    ).withDefaultValue(Set()),
    initState = 0,
    finalStates = Set(2),
  )

  // NFA for L = { w \in {0, 1}* | w contains exactly two 0's }
  def nfa_two_0: NFA = NFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // NFA for L = { w \in {0, 1}* | w contains "011" as a substring }
  def nfa_substr_011: NFA = NFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // NFA for L = { w \in {0, 1}* | w contains "00" or "11" }
  def nfa_has_00_or_11: NFA = NFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // ENFA for L = { a^i b^j c^k | i, j, k >= 0 }
  def enfa_ai_bj_ck: ENFA = ENFA(
    states = Set(0, 1, 2),
    symbols = Set('a', 'b', 'c'),
    trans = Map(
      (0, None) -> Set(1),
      (0, Some('a')) -> Set(0),
      (1, None) -> Set(2),
      (1, Some('b')) -> Set(1),
      (2, Some('c')) -> Set(2),
    ).withDefaultValue(Set()),
    initState = 0,
    finalStates = Set(2),
  )

  // ENFA for L = { (ab)^n | n >= 0 }
  def enfa_ab_plus: ENFA = ENFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // ENFA for L = { 0^n | n >= 0 } U { 1^n | n >= 0 }
  def enfa_same_digits: ENFA = ENFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )

  // ENFA for L = L_1^+
  // where
  // L_1 = { a w a | w \in L_2^+ }
  // L_2 = { b c^i b | i >= 1 }
  def enfa_complex: ENFA = ENFA(
    states = ???,
    symbols = ???,
    trans = ???,
    initState = ???,
    finalStates = ???,
  )
}
