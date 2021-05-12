func longestSubstring(s string, k int) int {
    dictsize := 256
    n := len(s)
    uniqLetters := countUniqLet(s, dictsize)
    ans := 0
    for currCount := 1; currCount <= uniqLetters; currCount++ {
        counter := make([]int, dictsize)
        diffCount := 0
        for start, end := 0,0; end < n; end++ {
            endIndex := s[end] - 'a'
            counter[endIndex]++
            if counter[endIndex] == 1 {
                diffCount++
            }
            for diffCount > currCount {
                startIndex := s[start] - 'a'
                counter[startIndex]--
                if counter[startIndex] == 0 {
                    diffCount--
                }
                start++
                
            }
            if isValid(counter, k) {
                currAns := end - start + 1
                if currAns > ans {
                    ans = currAns
                }
            }
            
        }
    }
    return ans

}
func countUniqLet(s string, dictsize int) int {
    count := make([]bool, dictsize)
    ans := 0
    for _, v := range s {
        count[v - 'a'] = true 
    }
    for _, v := range count {
        if v {
            ans++
        }
    }
    return ans
}
func isValid(counter []int, k int) bool {
    for _, v := range counter {
        if v > 0 && v < k {
            return false
        }
    }
    return true
}
