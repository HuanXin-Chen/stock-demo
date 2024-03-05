local FAIL_ATTACK = -1
local SUCCESS_ATTACK = 0
local FINISH_ATTACK = 1

local damage = tonumber(ARGV[1])
local round = tonumber(KEYS[1])
local monsterHpKey = 'monsterHp'
local monsterRoundKey = 'monsterRound'
local realDamage = damage

local monsterHp = tonumber(redis.call('get', monsterHpKey))
local monsterRound = tonumber(redis.call('get', monsterRoundKey))

if monsterRound ~= round then
    return {0, FAIL_ATTACK}
end

local newMonsterHp = monsterHp - damage

if newMonsterHp < 0 then
    realDamage = newMonsterHp + damage
    newMonsterHp = 50000
    redis.call('INCR', monsterRoundKey)
end

redis.call('set', monsterHpKey, newMonsterHp)

if newMonsterHp == 50000 then
    return {realDamage, FINISH_ATTACK}
end

return {realDamage, SUCCESS_ATTACK}