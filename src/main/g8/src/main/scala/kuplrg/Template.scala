package kuplrg

trait Template {
  def dfa_len_div_3: DFA
  def dfa_same_start_end: DFA
  def dfa_not_substr_101: DFA
  def dfa_div_4_1: DFA
  def nfa_comb_aaa_bb: NFA
  def nfa_101_or_110: NFA
  def nfa_has_aa_or_bb: NFA
  def enfa_opt_pre_post: ENFA
  def enfa_some_plus: ENFA
  def enfa_complex: ENFA
}
