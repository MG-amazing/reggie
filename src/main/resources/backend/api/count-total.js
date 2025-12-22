const countTotalMealAndTotalAmountByDate = (params) => {
    return $axios({
        url: '/countTotalMealAndTotalAmountByDate',
        method: 'post',
        data: params
    })
}
