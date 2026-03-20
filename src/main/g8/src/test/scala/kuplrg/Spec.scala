package kuplrg

import Implementation.*

class Spec extends SpecBase {

  // Number of trials for `mustEqual`
  private val TRIAL = 10_000

  // ---------------------------------------------------------------------------
  // Deterministic Finite Automata (DFA)
  // ---------------------------------------------------------------------------
  // L = { w \in {a, b}* | w has length divisible by 3 }
  val lang_len_div_3: Lang = Lang(
    "ab".toSet,
    w => w.length % 3 == 0,
  )

  // tests for `dfa_len_div_3`
  check(dfa_len_div_3.mustValid.mustEqual(lang_len_div_3, TRIAL))

  // L = { w \in {a, b}* | w starts and ends with the same symbol }
  val lang_same_start_end: Lang = Lang(
    "ab".toSet,
    w => w != "" && w.head == w.last,
  )

  // tests for `dfa_same_start_end`
  check(dfa_same_start_end.mustValid.mustEqual(lang_same_start_end, TRIAL))

  // L = { w \in {0, 1}* | w does NOT contain "101" as a substring }
  val lang_not_substr_101: Lang = Lang(
    "01".toSet,
    !_.contains("101"),
  )

  // tests for `dfa_not_substr_101`
  check(dfa_not_substr_101.mustValid.mustEqual(lang_not_substr_101, TRIAL))

  // L = { w \in {1, 2, 3}* | w \equiv 1 (mod 4) }
  // where w is interpreted as a number in base 10
  val lang_div_4_1: Lang = Lang(
    "123".toSet,
    w => w != "" && BigInt(w) % 4 == 1,
  )

  // tests for `dfa_div_4_1`
  check(dfa_div_4_1.mustValid.mustEqual(lang_div_4_1, TRIAL))

  // ---------------------------------------------------------------------------
  // Nondeterministic Finite Automata (NFA)
  // ---------------------------------------------------------------------------
  // L = { w \in {a, b}* | w is any combination of "aaa" and "bb" }
  val lang_comb_aaa_bb: Lang = Lang(
    "ab".toSet,
    "(aaa|bb)*".r.matches,
  )

  // tests for `nfa_comb_aaa_bb`
  check((nfa_comb_aaa_bb.mustValid <= 5).mustEqual(lang_comb_aaa_bb, TRIAL))

  // L = { w \in {0, 1}* | w ends with 101 or 110 }
  val lang_101_or_110: Lang = Lang(
    "01".toSet,
    w => w.endsWith("101") || w.endsWith("110"),
  )

  // tests for `nfa_101_or_110`
  check((nfa_101_or_110.mustValid <= 7).mustEqual(lang_101_or_110, TRIAL))

  // L = { w \in {a, b}* | w contains aa or bb as a substring }
  val lang_has_aa_or_bb: Lang = Lang(
    "ab".toSet,
    w => w.contains("aa") || w.contains("bb"),
  )

  // tests for `nfa_has_aa_or_bb`
  check((nfa_has_aa_or_bb.mustValid <= 8).mustEqual(lang_has_aa_or_bb, TRIAL))

  // ---------------------------------------------------------------------------
  // epsilon-Non-deterministic Finite Automata (epsilon-NFA)
  // ---------------------------------------------------------------------------
  // L = { \eps, aa } { c^n | n >= 0 } { \eps, bb }
  val lang_opt_pre_post: Lang = Lang(
    "abc".toSet,
    w => w.matches("(aa)?c*(bb)?"),
  )

  // tests for `enfa_opt_pre_post`
  check((enfa_opt_pre_post.mustValid <= 7).mustEqual(lang_opt_pre_post, TRIAL))

  // L = { aca, acb, bca, bcb }^+
  val lang_some_plus: Lang = Lang(
    "abc".toSet,
    w => w.matches("(aca|acb|bca|bcb)+"),
  )

  // tests for `enfa_some_plus`
  check((enfa_some_plus.mustValid <= 6).mustEqual(lang_some_plus, TRIAL))

  // L = L_1 \cap L_2
  // where
  // L_1 = { w \in {0, 1} | N(w) \equiv 1 (mod 3) }
  // L_2 = { w \in {0, 1} | zeros(w) \equiv 1 (mod 3) }
  // N(w) is the natural number represented by w in binary,
  // and zeros(w) is the number of 0's in w
  val lang_complex: Lang = Lang(
    "01".toSet,
    w => toInt(w) % 3 == 1 || w.count(_ == '0') % 3 == 1,
  )

  // tests for `enfa_complex`
  check((enfa_complex.mustValid <= 14).mustEqual(lang_complex, TRIAL))
}
