const countTotalMealByDate = (params) => {
    return $axios({
        url: '/countTotalMealByDate',
        method: 'post',
        data: params
    })
}
const countTotalAmountByDate = (params) => {
    return $axios({
        url: '/countTotalAmountByDate',
        method: 'post',
        data: params
    })
}